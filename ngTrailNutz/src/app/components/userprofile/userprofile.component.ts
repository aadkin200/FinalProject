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
  totalMiles: number = 0;
  editUser: User | null = null;
  selected: User | null = null;

  constructor(private auth: AuthService, private userSvc: UserService) { }

  ngOnInit(): void {
    this.userSvc.getUser().subscribe(
      user=>{
        this.user = user;
      }
      ,
      err=>{
        console.error("userprofile: ngOnInit(): error getting user", err);
      }
    )
  }

  getTotalMiles(user: User) {
    console.log(user.id);

    if (user.trails != undefined) {
      for(let i=0; i<user.trails.length; i++) {
        this.totalMiles+=user.trails[i].distanceMiles;
        console.log(user.trails[i].distanceMiles);

        console.log(this.totalMiles);

      }
    }
  }

  setEditUser(user: any) : void {
    this.editUser = user;
  }

  displayEdit(user: any): void {
    this.selected = user;
  }

  closeEdit(): void {
    this.selected = null;
  }

  updateUser(user: User) {
    this.userSvc.update(user).subscribe(
      data => {
        this.ngOnInit();
      },
      error => {
        console.log(error);
        console.log("error updating user through service")
      }
    );
    this.editUser = null;
    this.selected = null;
  }

  disableUser(userId: number) {
    this.userSvc.disable(userId).subscribe(
      error => {
        console.log(error);
        console.log("error disabling user through service")
      }
    );
  }

}
