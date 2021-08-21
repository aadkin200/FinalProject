import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginUser: User = new User();
  selected: User | null = null;


  constructor(private auth: AuthService,
              private router: Router
              ) { }

  ngOnInit(): void {
  }

  login(user: User) {
    this.auth.login(user.username, user.password).subscribe(
      loggedIn => {
        console.log('LoginComponent.login(): user logged in');
        this.router.navigateByUrl('/feed');
      },
      fial => {
        console.error("error in login.component.ts login()");
      }
    )
  }

  loggedIn(): boolean {
    return this.auth.checkLogin();
  }

}
