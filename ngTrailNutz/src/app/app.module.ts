import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { TrailFeedComponent } from './components/trail-feed/trail-feed.component';
import { TrailSinglePageComponent } from './components/trail-single-page/trail-single-page.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { NewTrailFormComponent } from './components/new-trail-form/new-trail-form.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AuthService } from './services/auth.service';
import { DifficultyService } from './services/difficulty.service';
import { AmenityService } from './services/amenity.service';
import { RouteTypeService } from './services/route-type.service';
import { AppRoutingModule } from './app-routing.module';
import { GoogleMapsModule } from '@angular/google-maps';
import { NgbCarousel, NgbDropdown, NgbModule } from '@ng-bootstrap/ng-bootstrap'
import { UserprofileComponent } from './components/userprofile/userprofile.component';
import { NgxBootstrapIconsModule } from 'ngx-bootstrap-icons';
import { imageFill, images, brush } from 'ngx-bootstrap-icons';
import { OrderModule } from 'ngx-order-pipe';
import { AdminViewComponent } from './components/admin-view/admin-view.component';



const icons = {
  imageFill,
  images,
  brush
};


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    LoginComponent,
    TrailFeedComponent,
    TrailSinglePageComponent,
    SignUpComponent,
    NewTrailFormComponent,
    NotFoundComponent,
    UserprofileComponent,
    AdminViewComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    HttpClientModule,
    GoogleMapsModule,
    NgbModule,
    NgxBootstrapIconsModule.pick(icons),
    OrderModule
  ],
  providers: [
    AuthService,
    DifficultyService,
    AmenityService,
    RouteTypeService,
    NgbCarousel,
    NgbDropdown,
    OrderModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
