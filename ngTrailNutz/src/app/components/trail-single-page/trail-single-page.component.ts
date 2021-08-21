import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Trail } from 'src/app/models/trail';
import { TrailService } from 'src/app/services/trail.service';

@Component({
  selector: 'app-trail-single-page',
  templateUrl: './trail-single-page.component.html',
  styleUrls: ['./trail-single-page.component.css']
})
export class TrailSinglePageComponent implements OnInit {
  trail:Trail = new Trail();
  constructor(private trailSvc: TrailService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    let trailId = this.activatedRoute.snapshot.params.trailId;
    this.getSingleTrail(trailId);
  }

  getSingleTrail(trailId:number):void {
    this.trailSvc.show(trailId).subscribe(
      data=>{
        this.trail = data;
        console.log(this.trail)
      },
      err=>{
        console.error(err, `No trail recieved singleComponent`)
      }
    )
  }

}
