import { Component, OnInit } from '@angular/core';
import { Trail } from 'src/app/models/trail';
import { TrailService } from 'src/app/services/trail.service';

@Component({
  selector: 'app-trail-feed',
  templateUrl: './trail-feed.component.html',
  styleUrls: ['./trail-feed.component.css']
})
export class TrailFeedComponent implements OnInit {

  trails: Trail[] = [];

  constructor(
    private trailSrv: TrailService
  ) { }

  ngOnInit(): void {
    this.loadFeed();
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
}
