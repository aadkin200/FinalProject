import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Amenity } from 'src/app/models/amenity';
import { Difficulty } from 'src/app/models/difficulty';
import { Routetype } from 'src/app/models/routetype';
import { Trail } from 'src/app/models/trail';
import { AmenityService } from 'src/app/services/amenity.service';
import { DifficultyService } from 'src/app/services/difficulty.service';
import { RouteTypeService } from 'src/app/services/route-type.service';
import { TrailService } from 'src/app/services/trail.service';

@Component({
  selector: 'app-new-trail-form',
  templateUrl: './new-trail-form.component.html',
  styleUrls: ['./new-trail-form.component.css']
})
export class NewTrailFormComponent implements OnInit {

  newTrail = new Trail();
  newDifficulties: Difficulty[] = [];
  newAmenities: Amenity[] = [];
  newRouteTypes: Routetype[] = [];

  newDifficulty = new Difficulty();
  newRouteType = new Routetype();



  constructor(private trailService: TrailService,
              private difficultyService: DifficultyService,
              private amenityService: AmenityService,
              private routeTypeService: RouteTypeService,
              private router: Router) { }

  ngOnInit(): void {
    this.difficultyService.show().subscribe(
      data => {
        this.newDifficulties = data;
      },
      error => {
        console.log("error in new-trail-form.component.ts ngOnInit()", error);
      }
    );

    this.amenityService.show().subscribe(
      data => {
        this.newAmenities = data;
      },
      error => {
        console.log("error in new-trail-form.component.ts ngOnInit()", error);
      }
    );

    this.routeTypeService.show().subscribe(
      data => {
        this.newRouteTypes = data;
      },
      error => {
        console.log("error in new-trail-form.component.ts ngOnInit()", error);
      }
    );
  }

  reload(trailId: any) {
    this.trailService.show(trailId).subscribe(
      data => {
        this.newTrail = data;
      },
      error => {
        console.log(error);
        console.log("error in new-trail-form.component.ts reload()")
      }
    );
  }

  addTrail(): void {
    this.newTrail.difficulty = this.newDifficulty;
    this.newTrail.routeType = this.newRouteType;
    console.log(this.newTrail);
    this.trailService.create(this.newTrail).subscribe(
      data => {
        this.router.navigateByUrl(`trail/${data.id}`);
      },
      error => {
        console.log(error);
        console.log("error in new-trail-form.component.ts addTrail()")
      }
    );
    this.newTrail = new Trail();
  }

}
