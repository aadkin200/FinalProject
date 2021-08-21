import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  newUser:User = new User();

  constructor(private auth: AuthService, private router: Router
    ) {
   }

  ngOnInit(): void {
  }

  register(){
      this.removeEmpty();
      this.auth.register(this.newUser).subscribe(
        data => {
          this.auth.login(this.newUser.username, this.newUser.password).subscribe(
            info=>{
              this.router.navigateByUrl("/profile")
              this.newUser = new User();
            }, fail=> {
              console.log("error creating account", fail)
            }
          )

        },
        error => {
          console.log(error);
          console.log("error")
        }
      );

    }
    removeEmpty(){
      delete this.newUser.role;
      delete this.newUser.enabled;
      delete this.newUser.favoriteTrailFood;
      delete this.newUser.firstName;
      delete this.newUser.lastName;
      delete this.newUser.favoriteTrails;
      delete this.newUser.imageUrl;
      delete this.newUser.trails;
      delete this.newUser.comments;
      delete this.newUser.trailImages;
      delete this.newUser.trailResouces;
    }





  }





