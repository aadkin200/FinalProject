import { Component, OnInit } from '@angular/core';
import { Trail } from 'src/app/models/trail';
import { TrailService } from 'src/app/services/trail.service';

@Component({
  selector: 'app-new-trail-form',
  templateUrl: './new-trail-form.component.html',
  styleUrls: ['./new-trail-form.component.css']
})
export class NewTrailFormComponent implements OnInit {

  newTrail = new Trail();
  constructor(private trailService: TrailService) { }

  ngOnInit(): void {
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
