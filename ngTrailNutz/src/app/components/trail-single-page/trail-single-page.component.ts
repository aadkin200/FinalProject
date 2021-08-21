import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Trail } from 'src/app/models/trail';
import { TrailImage } from 'src/app/models/trail-image';
import { TrailService } from 'src/app/services/trail.service';

@Component({
  selector: 'app-trail-single-page',
  templateUrl: './trail-single-page.component.html',
  styleUrls: ['./trail-single-page.component.css'],
})
export class TrailSinglePageComponent implements OnInit {
  trail: Trail = new Trail();
  mapOptions: google.maps.MapOptions = {
    center: { lat: 38.9987208, lng: -77.2538699 },
    zoom: 14,
  };
  marker = {
    position: { lat: 38.9987208, lng: -77.2538699 },
  };
  mainImg:string = "";

  constructor(
    private trailSvc: TrailService,
    private activatedRoute: ActivatedRoute,
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
        console.log(this.trail);
        this.mapOptions.center = { lat: parseFloat(this.trail.trailheadLatitude), lng: parseFloat(this.trail.trailheadLongitude)};
        this.marker.position = { lat: parseFloat(this.trail.trailheadLatitude), lng: parseFloat(this.trail.trailheadLongitude)};
        this.mainImg = this.trail.trailImages[0].imageUrl;
      },
      (err) => {
        console.error(err, `No trail recieved singleComponent`);
      }
    );
  }

  changeMainImg(image:TrailImage){
    this.mainImg = image.imageUrl;
  }

}
