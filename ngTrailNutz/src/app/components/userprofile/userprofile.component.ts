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
  editUser: User = new User();
  selected: User | null = null;

  constructor(private auth: AuthService, private userSvc: UserService) { }

  ngOnInit(): void {
   this.reload();
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


  setSelected(user: any) : void {
    this.selected = user;
    this.displayEdit(user);
  }


  displayEdit(user: any): void {
    this.editUser = user;
  }

  closeSelected(): void {
    this.selected = null;
  }

  updateUser(user: User) {
    console.log(this.editUser);

    this.removeEmpty();
    this.userSvc.update(this.editUser).subscribe(
      data=>{
        this.reload();
      },
      error => {
        console.log(error);
        console.log("error updating user through service")
      }
    );
    this.selected = null;
  }

  // disableUser(userId: number) {

  //   this.userSvc.disable(userId).subscribe(
  //     error => {
  //       console.log(error);
  //       console.log("error disabling user through service")
  //     }
  //   );
  // }

  removeEmpty(){
    delete this.editUser.role;
    delete this.editUser.enabled;
    // delete this.editUser.favoriteTrailFood;
    // delete this.editUser.firstName;
    // delete this.editUser.lastName;
    delete this.editUser.favoriteTrails;
    // delete this.editUser.imageUrl;
    delete this.editUser.trails;
    delete this.editUser.comments;
    delete this.editUser.trailImages;
    delete this.editUser.trailResouces;
  }

  reload() {
    this.userSvc.getUser().subscribe(
      data => {
        this.user = data;
      },
      err => {
        console.log("Error retreiving user from service")
      }
    );
  }
}
