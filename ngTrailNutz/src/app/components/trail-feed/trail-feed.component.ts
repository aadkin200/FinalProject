import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Trail } from 'src/app/models/trail';
import { TrailImage } from 'src/app/models/trail-image';
import { TrailImageService } from 'src/app/services/trail-image.service';
import { TrailService } from 'src/app/services/trail.service';
import { NgbCarousel, NgbSlideEvent, NgbSlideEventSource } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-trail-feed',
  templateUrl: './trail-feed.component.html',
  styleUrls: ['./trail-feed.component.css']
})
export class TrailFeedComponent implements OnInit {

  trails: Trail[] = [];

  newTrailFormBool: boolean = false;

  paused = false;
  unpauseOnArrow = false;
  pauseOnIndicator = false;
  pauseOnHover = true;
  pauseOnFocus = true;

  // @ViewChild('carousel', {static : true}) carousel: NgbCarousel;

  constructor(
    private trailSrv: TrailService,
    private carousel: NgbCarousel,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadFeed();
  }

  newTrailFormFillOut() {
    this.newTrailFormBool = true;
  }

  closeTrailFormFillOut() {
    this.newTrailFormBool = false;
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

  togglePaused() {
    if (this.paused) {
      this.carousel.cycle();
    } else {
      this.carousel.pause();
    }
    this.paused = !this.paused;
  }

  onSlide(slideEvent: NgbSlideEvent) {
    if (this.unpauseOnArrow && slideEvent.paused &&
      (slideEvent.source === NgbSlideEventSource.ARROW_LEFT || slideEvent.source === NgbSlideEventSource.ARROW_RIGHT)) {
      this.togglePaused();
    }
    if (this.pauseOnIndicator && !slideEvent.paused && slideEvent.source === NgbSlideEventSource.INDICATOR) {
      this.togglePaused();
    }
  }

}
