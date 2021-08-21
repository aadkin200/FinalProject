import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Trail } from 'src/app/models/trail';
import { TrailImage } from 'src/app/models/trail-image';
import { CommentService } from 'src/app/services/comment.service';
import { TrailService } from 'src/app/services/trail.service';

@Component({
  selector: 'app-trail-single-page',
  templateUrl: './trail-single-page.component.html',
  styleUrls: ['./trail-single-page.component.css'],
})
export class TrailSinglePageComponent implements OnInit {
  trail: Trail = new Trail();
  mainImage:string = "";
  mapOptions: google.maps.MapOptions = {
    zoom: 14
  };
  latlng = { lat: 0, lng: 0 };
  marker = {
    position: { lat: 0, lng: 0 },
  };

  constructor(
    private trailSvc: TrailService,
    private activatedRoute: ActivatedRoute,
    private commentSvc: CommentService
  ) {}

  trailLat: string = '';
  trailLong: string = '';
  ngOnInit(): void {
    let trailId = this.activatedRoute.snapshot.params.trailId;
    this.getSingleTrail(trailId);
  }

  getSingleTrail(trailId: number): void {
    this.trailSvc.show(trailId).subscribe(
      (data) => {
        this.trail = data;
        this.mainImage = this.trail.trailImages[0].imageUrl;
        this.changeMapCord();
        console.log(this.trail)
      },
      (err) => {
        console.error(err, `No trail recieved singleComponent`);
      }
    );
  }

  changeMainImg(image:TrailImage){
    this.mainImage = image.imageUrl;
  }
  changeMapCord(){
        this.latlng = { lat: parseFloat(this.trail.trailheadLatitude), lng: parseFloat(this.trail.trailheadLongitude)};
        this.marker.position = { lat: parseFloat(this.trail.trailheadLatitude), lng: parseFloat(this.trail.trailheadLongitude)};
  }
}
