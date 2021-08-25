import { Component, OnInit, Type } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
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
import { TrailResource } from 'src/app/models/trail-resource';
import { TrailResourceService } from 'src/app/services/trail-resource.service';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';

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
  editComments: boolean[] = [];
  currentEditComment: Comment = new Comment();
  isEditing: boolean = false;
  newDifficulties: Difficulty[] = [];
  tr:TrailResource = new TrailResource;
  newRoutes: Routetype[] = [];
  userEditComment: Comment[] = [];
  newRoute: Routetype = new Routetype();
  newDifficulty: Difficulty = new Difficulty();
  editingTrail = new Trail();
  trailImage: TrailImage = new TrailImage();
  trailLat: string = '';
  trailLong: string = '';
  closeModal: string = '';
  userFavorite:boolean = false;
  mapOptions: google.maps.MapOptions = {
    zoom: 14,
  };
  latlng = { lat: 0, lng: 0 };
  marker = {
    position: { lat: 0, lng: 0 },
  };



  constructor(
    private trailSvc: TrailService,
    private activatedRoute: ActivatedRoute,
    private trailImgsvc: TrailImageService,
    private userSvc: UserService,
    private authSvc: AuthService,
    private commentSvc: CommentService,
    private orderPipe: OrderPipe,
    private difficultyService: DifficultyService,
    private routeService: RouteTypeService,
    private trailResSvc: TrailResourceService,
    private modalService: NgbModal,
    private router: Router
  ) {}



  ngOnInit(): void {
    let trailId = this.activatedRoute.snapshot.params.trailId;
    this.getSingleTrail(trailId);
    if(this.authSvc.checkLogin()){
      this.checkForUser();
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
  }

  setUserFavorite(){
    this.userFavorite = !this.userFavorite;
    this.userSvc.setUserFavorite(this.trail.id).subscribe(
      fav=>{
        this.getSingleTrail(this.trail.id);
      },
      err=>{
        console.error("Error updating favorite status single page app");
      }
    )
  }

  deleteTrailResource(trailRId:number){
    this.trailResSvc.disable(trailRId, this.trail.id).subscribe(
      success=>{
        this.getSingleTrail(this.trail.id);
      },
      err=>{
        console.error("Error deleting trail Resource");
      }
    )
  }

  isUserFavorite(){
    if(this.loggedInUser.favoriteTrails){
      this.loggedInUser.favoriteTrails.forEach(trail => {
        if(trail.id == this.trail.id){
          this.userFavorite = true;
          return;
        }
      })
    }

  }

  checkForUser(){
    if(this.authSvc.checkLogin()){
      this.userSvc.getUser().subscribe(
        (user) => {
          this.loggedInUser = user;
          this.isUserFavorite();
        },
        (err) => {
          console.error('SinglePageView: ngOnInit(): error getting user', err);
        }
      );
    }
  }

  triggerModal(content:any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((res) => {
      this.closeModal = `Closed with: ${res}`;
    }, (res) => {
      this.closeModal = `Dismissed ${this.getDismissReason(res)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }

  deleteTrail(){
    this.trailSvc.destroy(this.trail.id).subscribe(
      del=>{
        this.router.navigateByUrl("/feed");
      },
      err=>{
        console.error("problem deleting trail singlepage", err);
      }
    )
  }

  addTrailResource(){
    if(!this.tr.title){
      this.tr.title = "N/A";
    }
    this.trailResSvc.create(this.tr, this.trail.id).subscribe(
      resource=>{
        console.log(resource);
        this.getSingleTrail(this.trail.id);
        this.tr = new TrailResource();
      },
      err => {
        console.error("Error creating trail resource: addTrailResource(): SinglePageView")
      }
    )
  }

  getSingleTrail(trailId: number): void {
    this.trailSvc.show(trailId).subscribe(
      (data) => {
        this.trail = data;
        this.newDifficulty = this.trail.difficulty;
        this.newRoute = this.trail.routeType;
        if(this.trail.comments != undefined){
        this.trail.comments = this.trail.comments.filter(comment => comment.enabled);
        }
        this.changeMapCord();
        this.editingTrail = Object.assign({}, this.trail);
        this.createBoolArray();
        if(this.trail.trailImages != undefined){
          if(this.trail.trailImages.length == 0){
            let trailImage = new TrailImage();
            trailImage.imageUrl = 'http://blog.archive.org/wp-content/uploads/2016/08/nomor4041.png';
            this.mainTrailImage = trailImage;
          }else {
            this.changeMainImg(this.trail.trailImages[0]);
          }
        }

        if(this.trail.comments != undefined){
        this.trail.comments = this.orderPipe.transform(
          this.trail.comments,
          this.trail.comments.forEach((com) => com.createdAt)
        );
        }
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

  submitEditComment(id:number){
    this.commentSvc.update(this.userEditComment[id], this.trail.id).subscribe(
      data=> {
        this.getSingleTrail(this.trail.id);
      },
      err => {
        console.error("submit edit comment error single page", err);
      }
    )
  }

  deleteComment(commentId:number){
    this.commentSvc.disable(this.trail.id, commentId).subscribe(
      del=>{
        this.getSingleTrail(this.trail.id);
      },
      err=>{
        console.error("Error deleting comment", err)
      }
    )
  }

  editComment(com:Comment, i:number){
    this.editComments[com.id] = !this.editComments[com.id];
    this.userEditComment[com.id] = Object.assign({}, com);
  }

  createBoolArray() {
    if(this.trail.comments != undefined){
    for (let i = 0; i < this.trail.comments.length; i++) {
      this.replyCollapse.push(false);
      this.editComments[this.trail.comments[i].id] = false;
      // create edit comments
      this.userEditComment[this.trail.comments[i].id] = new Comment();
    }
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
        this.getSingleTrail(this.trail.id);
        this.currentComment = new Comment();
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
