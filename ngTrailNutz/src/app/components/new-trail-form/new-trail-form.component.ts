import { Component, OnInit } from '@angular/core';
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
  newDifficulty: Difficulty[] = [];
  newAmenities: Amenity[] = [];
  newRouteType: Routetype[] = [];


  constructor(private trailService: TrailService,
              private difficultyService: DifficultyService,
              private amenityService: AmenityService,
              private routeType: RouteTypeService) { }

  ngOnInit(): void {
    this.difficultyService.show().subscribe(
      data => {
        this.newDifficulty = data;
      },
      error => {
        console.log(error);
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
    this.trailService.create(this.newTrail).subscribe(
      data => {
        this.reload(this.newTrail.id);
      },
      error => {
        console.log(error);
        console.log("error in new-trail-form.component.ts addTrail()")
      }
    );
    this.newTrail = new Trail();
  }

}
