import { localizedString } from '@angular/compiler/src/output/output_ast';
import { Component, ElementRef, NgZone, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Amenity } from 'src/app/models/amenity';
import { Difficulty } from 'src/app/models/difficulty';
import { Routetype } from 'src/app/models/routetype';
import { Trail } from 'src/app/models/trail';
import { TrailImage } from 'src/app/models/trail-image';
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
  @ViewChild('newMap') gmapElement: any;

  lat = 0;
  lng = 0;
  zoom = 15;
  geoCoder = new google.maps.Geocoder;

  newTrail = new Trail();
  newDifficulties: Difficulty[] = [];
  newAmenities: Amenity[] = [];
  newRouteTypes: Routetype[] = [];

  newDifficulty = new Difficulty();
  newRouteType = new Routetype();

  formText = '';

  mapOptions: google.maps.MapOptions = {
    zoom: 14
  }

  latlng = { lat: this.lat, lng: this.lng};
  marker = {
    position: {lat: 0, lng: 0}
  };

  // latlng: [] | any = null;
  // marker: [] | any = null;

  trailLat: string='';
  trailLong: string='';

  // map = document.getElementById('newMap');
  // mapMarker = document.getElementById('mapMarker');

  // map = new google.maps.Map(
  //   document.getElementById("newMap") as HTMLElement,
  //   {
  //     center: {lat: this.lat, lng: this.lng},
  //     zoom: this.mapOptions.zoom
  //   }
  // )




  constructor(private trailService: TrailService,
              private difficultyService: DifficultyService,
              private amenityService: AmenityService,
              private routeTypeService: RouteTypeService,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private ngZone: NgZone)
 { }



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

    const autocompleteFieldForm = document.getElementById('autoComplete');
    const autocomplete = new google.maps.places.Autocomplete(autocompleteFieldForm as HTMLInputElement,
   {

   });

    google.maps.event.addListener(autocomplete, `place_changed`, () => {
      const place = autocomplete.getPlace();
      this.latlng.lat = parseFloat(place.geometry?.location.lat()+'');
      this.latlng.lng = parseFloat(place.geometry?.location.lng()+'');
      this.marker.position.lat = parseFloat(place.geometry?.location.lat()+'');
      this.marker.position.lng = parseFloat(place.geometry?.location.lng()+'');
      let map = document.getElementById('newMap');
      this.viewTestCoordinates(this.marker.position.lat);


      this.newTrail.trailheadLatitude = place.geometry?.location.lat()+'';
      this.newTrail.trailheadLongitude = place.geometry?.location.lng()+'';


    })

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

  viewTestCoordinates(test: any) {
    console.log(test);

  }

}
