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
import { FormsModule } from '@angular/forms';
import { TrailImageService } from 'src/app/services/trail-image.service';

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

  newTrail = new Trail() as Trail;
  newDifficulties: Difficulty[] = [];
  newAmenities: Amenity[] = [];
  newRouteTypes: Routetype[] = [];
  addToTrailAmenities: Amenity[] = [];

  newDifficulty = new Difficulty();
  newRouteType = new Routetype();

  bathroomAmenity = new Amenity();
  roadExitAmenity = new Amenity();
  waterSourceAmenity = new Amenity();

  formText = '';

  mapOptions: google.maps.MapOptions = {
    zoom: 14
  }

  latlng = { lat: this.lat, lng: this.lng};
  marker = {
    position: {lat: 0, lng: 0}
  };


  trailLat: string='';
  trailLong: string='';







  constructor(private trailService: TrailService,
              private difficultyService: DifficultyService,
              private amenityService: AmenityService,
              private routeTypeService: RouteTypeService,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private ngZone: NgZone,
              private trailImageService: TrailImageService)
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
      this.viewTestCoordinates(place);


      this.newTrail.trailheadLatitude = place.geometry?.location.lat()+'';
      this.newTrail.trailheadLongitude = place.geometry?.location.lng()+'';
      if(place.address_components) {
        for(let i=0; i<place.address_components?.length; i++) {
          if(place.address_components[i].types[0] === "administrative_area_level_1") {
            this.newTrail.state = place.address_components[i].short_name;
          }
          if(place.address_components[i].types[0] === "locality") {
            this.newTrail.city = place.address_components[i].long_name;
          }
        }
      }
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
    this.newTrail.amenities = this.addToTrailAmenities;
    console.log(this.newTrail);
    let newTrailImage: TrailImage = new TrailImage();
    newTrailImage.imageUrl = 'https://static.wixstatic.com/media/2cd43b_457a179ee0c64a7ba7ed0e41bb344359~mv2_d_1969_1582_s_2.png/v1/fill/w_320,h_256,q_90/2cd43b_457a179ee0c64a7ba7ed0e41bb344359~mv2_d_1969_1582_s_2.png';
    this.trailService.create(this.newTrail).subscribe(
      data => {
        // this.amenityService.addAmenity(this.newTrail.amenities, data.id).subscribe(
        //   dataAmen => {
        //     console.log('successful amen add');

        //   },
        //   error => {

        //   }
        // )
        this.trailImageService.addImage(newTrailImage, data.id).subscribe(
          dataimage => {
            this.router.navigateByUrl(`trail/${data.id}`);
          },
          error => {
            console.log(error);
            console.log('error in new-trail-form.component.ts addTrail()');
          }
        )
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

  pushOrPop(amenity:Amenity, event:any){
    if(event.target.checked){
      this.addToTrailAmenities.push(amenity);
    }else {
      let index:number = this.addToTrailAmenities.indexOf(amenity);
      this.addToTrailAmenities.splice(index,1);
    }
  }
}
