import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Trail } from 'src/app/models/trail';
import { TrailService } from 'src/app/services/trail.service';

@Component({
  selector: 'app-trail-feed',
  templateUrl: './trail-feed.component.html',
  styleUrls: ['./trail-feed.component.css']
})
export class TrailFeedComponent implements OnInit {

  trails: Trail[] = [];
  newTrailFormBool: boolean = false;
  constructor(
    private trailSrv: TrailService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadFeed();
  }

  newTrailFormFillOut() {
    this.newTrailFormBool = true;
  }

  loadFeed(){
    this.trailSrv.index().subscribe(
      trails => {
        this.trails = trails;
      },
      noTrails => {
        console.log("Error retreiving trails from service");
        console.log(noTrails);

      }
    );
  }

  displayTrail(trail: Trail){

    this.router.navigateByUrl(`trail/${trail.id}`);

  }
}
