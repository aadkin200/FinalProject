import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  user: User = new User();
  admin: boolean = false;


  constructor(private auth: AuthService, private userSvc: UserService) { }

  ngOnInit(): void {
      this.userSvc.getUser().subscribe(
        data => {
          this.user = data;
          this.admin = this.isAdmin();
          console.log(this.admin);

        },
        err => {
          console.log("Error retreiving user from service")
        }
      );
  }

  loggedIn(): boolean {
    return this.auth.checkLogin();
  }

  logout() {
    this.admin = false;
    this.user = new User();
    this.auth.logout();

  }

<<<<<<< HEAD
=======
  isAdmin(): boolean{
    // console.log(this.user.role);
    if (this.user.role === "ADMIN") {
      return true;

    } else {
      return false;
    }
>>>>>>> 865e6aee7119adc36244bc6d211d11dc61cc2ae0
  }
}


