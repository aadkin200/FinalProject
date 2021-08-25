import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminViewComponent } from './components/admin-view/admin-view.component';
import { HomeComponent } from './components/home/home.component';
import { NewTrailFormComponent } from './components/new-trail-form/new-trail-form.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { TrailFeedComponent } from './components/trail-feed/trail-feed.component';
import { TrailSinglePageComponent } from './components/trail-single-page/trail-single-page.component';
import { UserprofileComponent } from './components/userprofile/userprofile.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomeComponent},
  { path: 'signup', component: SignUpComponent},
  { path: 'profile', component: UserprofileComponent},
  { path: 'feed', component: TrailFeedComponent},
  { path: 'trail/:trailId', component: TrailSinglePageComponent},
  { path: 'newtrail', component: NewTrailFormComponent},
  { path: 'admin', component: AdminViewComponent},
  { path: '**', component: NotFoundComponent},
];
@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
