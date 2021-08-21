import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Trail } from 'src/app/models/trail';
import { TrailImage } from 'src/app/models/trail-image';
import { TrailImageService } from 'src/app/services/trail-image.service';
import { TrailService } from 'src/app/services/trail.service';

@Component({
  selector: 'app-trail-feed',
  templateUrl: './trail-feed.component.html',
  styleUrls: ['./trail-feed.component.css']
})
export class TrailFeedComponent implements OnInit {

  trails: Trail[] = [];
  // trailImages: TrailImage[] = [];

  constructor(
    private trailSrv: TrailService,
    // private imageSrv: TrailImageService,
    private router: Router
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
  //   this.trailSrv.index().subscribe(
  //     trails => {
  //       this.trailImages = trails.
  //     }
  //   );
  }

  displayTrail(trail: Trail){

    this.router.navigateByUrl(`trail/${trail.id}`);

  }

}
