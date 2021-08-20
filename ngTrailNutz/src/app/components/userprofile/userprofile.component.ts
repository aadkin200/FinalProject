import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-userprofile',
  templateUrl: './userprofile.component.html',
  styleUrls: ['./userprofile.component.css']
})
export class UserprofileComponent implements OnInit {
  user:User = new User();
  constructor(private auth: AuthService, private userSvc: UserService) { }

  ngOnInit(): void {
    this.userSvc.getUser().subscribe(
      user=>{
        this.user = user;
      },
      err=>{
        console.error("userprofile: ngOnInit(): error getting user", err);
      }
    )
  }



}
