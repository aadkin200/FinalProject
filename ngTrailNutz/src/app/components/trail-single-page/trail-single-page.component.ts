import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Trail } from 'src/app/models/trail';
import { TrailImage } from 'src/app/models/trail-image';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { CommentService } from 'src/app/services/comment.service';
import { TrailImageService } from 'src/app/services/trail-image.service';
import { TrailService } from 'src/app/services/trail.service';
import { UserService } from 'src/app/services/user.service';
import { Comment } from 'src/app/models/comment';
import { OrderPipe } from 'ngx-order-pipe';
import { DifficultyService } from 'src/app/services/difficulty.service';
import { Difficulty } from 'src/app/models/difficulty';
import { Routetype } from 'src/app/models/routetype';
import { RouteTypeService } from 'src/app/services/route-type.service';

@Component({
  selector: 'app-trail-single-page',
  templateUrl: './trail-single-page.component.html',
  styleUrls: ['./trail-single-page.component.css'],
})
export class TrailSinglePageComponent implements OnInit {
  loggedInUser: User = new User();
  trail: Trail = new Trail();
  currentComment = new Comment();
  topComment = new Comment();
  mainTrailImage: TrailImage = new TrailImage();
  replyCollapse: boolean[] = [];
  isEditing: boolean = false;
  newDifficulties: Difficulty[] = [];
  newRoutes: Routetype[] = [];
  newRoute: Routetype = new Routetype();
  newDifficulty: Difficulty = new Difficulty();
  editingTrail = new Trail();

  mapOptions: google.maps.MapOptions = {
    zoom: 14,
  };
  latlng = { lat: 0, lng: 0 };
  marker = {
    position: { lat: 0, lng: 0 },
  };

  trailImage: TrailImage = new TrailImage();
  isCollapsed: boolean = false;
  constructor(
    private trailSvc: TrailService,
    private activatedRoute: ActivatedRoute,
    private trailImgsvc: TrailImageService,
    private userSvc: UserService,
    private authSvc: AuthService,
    private commentSvc: CommentService,
    private orderPipe: OrderPipe,
    private difficultyService: DifficultyService,
    private routeService: RouteTypeService
  ) {}

  trailLat: string = '';
  trailLong: string = '';
  ngOnInit(): void {
    let trailId = this.activatedRoute.snapshot.params.trailId;
    this.getSingleTrail(trailId);
    this.userSvc.getUser().subscribe(
      (user) => {
        this.loggedInUser = user;
      },
      (err) => {
        console.error('SinglePageView: ngOnInit(): error getting user', err);
      }
    );

    this.difficultyService.show().subscribe(
      (data) => {
        this.newDifficulties = data;
      },
      (error) => {
        console.log('error singleTrail ngOnInit() difficulty', error);
      }
    );

    this.routeService.show().subscribe(
      (data) => {
        this.newRoutes = data;
      },
      (error) => {
        console.log('error singleTrail ngOnInit() routeType', error);
      }
    );
  }

  getSingleTrail(trailId: number): void {
    this.trailSvc.show(trailId).subscribe(
      (data) => {
        this.trail = data;
        if (this.trail.trailImages.length > 0) {
          this.mainTrailImage = this.trail.trailImages[0];
        } else {
          this.mainTrailImage.imageUrl = `https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/600px-No_image_available.svg.png`;
        }
        this.newDifficulty = this.trail.difficulty;
        this.newRoute = this.trail.routeType;
        this.changeMapCord();
        this.editingTrail = Object.assign({}, this.trail);
        this.createBoolArray();
        this.trail.comments = this.orderPipe.transform(
          this.trail.comments,
          this.trail.comments.forEach((com) => com.createdAt)
        );
      },
      (err) => {
        console.error(err, `No trail recieved singleComponent`);
      }
    );
  }

  editTrail() {
    this.isEditing = false;
    for (let d of this.newDifficulties) {
      if (this.newDifficulty.name === d.name) {
        this.editingTrail.difficulty = d;
      }
    }
    for (let r of this.newRoutes) {
      if (this.newRoute.name === r.name) {
        this.editingTrail.routeType = r;
      }
    }
    this.trailSvc.update(this.editingTrail).subscribe(
      (update) => {
        this.getSingleTrail(this.trail.id);
      },
      (err) => {
        console.error('error updating trail', err);
      }
    );
  }

  changeMainImg(image: TrailImage) {
    this.mainTrailImage = image;
  }

  changeMapCord() {
    // console.log(this.trail.trailheadLongitude + '-----------------------long');
    // console.log(this.trail.trailheadLatitude + '-------------------------lat');
    // console.log(parseFloat(this.trail.trailheadLatitude) + '---------------parse lat');
    // console.log(parseFloat(this.trail.trailheadLatitude));
    this.latlng = {
      lat: parseFloat(this.trail.trailheadLatitude),
      lng: parseFloat(this.trail.trailheadLongitude),
    };
    this.marker.position = {
      lat: parseFloat(this.trail.trailheadLatitude),
      lng: parseFloat(this.trail.trailheadLongitude),
    };
  }

  addTrailImage() {
    this.trailImgsvc.addImage(this.trailImage, this.trail.id).subscribe(
      (trailImg) => {
        this.getSingleTrail(this.trail.id);
        this.trailImage = new TrailImage();
      },
      (err) => {
        console.log('Error creating TrailImage:addTrailImage() singlepage');
      }
    );
  }

  deleteTrailImage() {
    this.trailImgsvc.removeImage(this.mainTrailImage, this.trail.id).subscribe(
      (del) => {
        this.getSingleTrail(this.trail.id);
      },
      (err) => {
        console.error(
          'Error Deleting Trail Image:deleteTrailImage() single page'
        );
      }
    );
  }

  isLoggedIn(): boolean {
    return this.authSvc.checkLogin();
  }

  createBoolArray() {
    for (let i = 0; i < this.trail.comments.length; i++) {
      this.replyCollapse.push(false);
    }
  }

  postComment() {
    this.removeProperties();
    this.commentSvc.create(this.topComment, this.trail.id).subscribe(
      (success) => {
        this.getSingleTrail(this.trail.id);
        this.clearCommentBlock();
      },
      (err) => {
        console.log(`error creating comment`);
      }
    );
  }

  clearCommentBlock() {
    this.currentComment = new Comment();
    this.topComment = new Comment();
  }

  postReply(parentComment: Comment) {
    this.currentComment.parentComment = parentComment;
    this.removeProperties();
    this.commentSvc.create(this.currentComment, this.trail.id).subscribe(
      (success) => {
        this.commentSvc.getReply(parentComment.id).subscribe(
          (reply) => {
            this.trail.comments.forEach((comment) => {
              if (comment.id == parentComment.id) {
                comment.replies = reply;
                this.clearCommentBlock();
              }
            });
          },
          (error) => {}
        );
      },
      (err) => {
        console.error(err);
      }
    );
  }

  removeProperties() {
    delete this.topComment.createdAt;
    delete this.topComment.updatedAt;
    delete this.currentComment.createdAt;
    delete this.currentComment.updatedAt;
  }
}
