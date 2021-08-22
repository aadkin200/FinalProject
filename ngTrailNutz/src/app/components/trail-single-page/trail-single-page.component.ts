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


@Component({
  selector: 'app-trail-single-page',
  templateUrl: './trail-single-page.component.html',
  styleUrls: ['./trail-single-page.component.css'],
})
export class TrailSinglePageComponent implements OnInit {
  loggedInUser: User = new User();
  trail: Trail = new Trail();
  currentComment = new Comment();
  mainTrailImage:TrailImage = new TrailImage;
  replyCollapse:boolean[] = [];

  mapOptions: google.maps.MapOptions = {
    zoom: 14
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
    private commentSvc: CommentService
  ) {}

  trailLat: string = '';
  trailLong: string = '';
  ngOnInit(): void {
    let trailId = this.activatedRoute.snapshot.params.trailId;
    this.getSingleTrail(trailId);
    this.userSvc.getUser().subscribe(
      user=>{
        this.loggedInUser = user;
      }
      ,
      err=>{
        console.error("SinglePageView: ngOnInit(): error getting user", err);
      }
    )

  }

  getSingleTrail(trailId: number): void {
    this.trailSvc.show(trailId).subscribe(
      (data) => {
        this.trail = data;
        this.mainTrailImage = this.trail.trailImages[0];
        this.changeMapCord();
        this.createBoolArray();
        console.log(this.replyCollapse)
      },
      (err) => {
        console.error(err, `No trail recieved singleComponent`);
      }
    );
  }

  changeMainImg(image:TrailImage){
    this.mainTrailImage = image;
  }
  changeMapCord(){
        this.latlng = { lat: parseFloat(this.trail.trailheadLatitude), lng: parseFloat(this.trail.trailheadLongitude)};
        this.marker.position = { lat: parseFloat(this.trail.trailheadLatitude), lng: parseFloat(this.trail.trailheadLongitude)};
  }

  addTrailImage(){
    this.trailImgsvc.addImage(this.trailImage, this.trail.id).subscribe(
      trailImg=>{
        this.getSingleTrail(this.trail.id);
        this.trailImage = new TrailImage;
      },
      err=>{
        console.log("Error creating TrailImage:addTrailImage() singlepage");
      }
    )
  }

  deleteTrailImage(){
    this.trailImgsvc.removeImage(this.mainTrailImage, this.trail.id).subscribe(
      del=>{
        this.getSingleTrail(this.trail.id);
      },
      err=>{
        console.error("Error Deleting Trail Image:deleteTrailImage() single page");
      }
    )
  }

  isLoggedIn():boolean{
    return this.authSvc.checkLogin();
  }

  createBoolArray(){
    for(let i = 0; i < this.trail.comments.length; i++){
      this.replyCollapse.push(false);
    }
  }
  test(id:number){
    console.log(id);
  }

  postReply(parentComment:Comment){
    this.currentComment.parentComment = parentComment;
    delete this.currentComment.createdAt;
    delete this.currentComment.updatedAt;
    console.log(parentComment);
    this.commentSvc.create(this.currentComment, this.trail.id).subscribe(
      success=>{
        this.commentSvc.getReply(parentComment.id).subscribe(
          reply=>{
            this.trail.comments.forEach(comment => {
              if(comment.id == parentComment.id){
                comment.replies = reply;
              }
            })
          },
          error=>{

          }
        )
      },
      err=>{
        console.error(err);

      }
    )
  }
}
