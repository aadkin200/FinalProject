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

  user:User = new User();
  newUser:User = new User();

  constructor(private auth: AuthService, private router: Router
    ) {
   }

  ngOnInit(): void {
  }

  register(newUser: User){

      this.auth.register(this.newUser).subscribe(
        data => {
          console.log(newUser.username , newUser.password)
          this.auth.login(newUser.username, newUser.password).subscribe(
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






  }





