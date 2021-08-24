import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-admin-view',
  templateUrl: './admin-view.component.html',
  styleUrls: ['./admin-view.component.css']
})
export class AdminViewComponent implements OnInit {

  users:User[] = [];
  user: User = new User();

  constructor(private userSvc: UserService) { }

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(){
    this.userSvc.getAllUsers().subscribe(
      data => {
        this.users = data;
      },
      noUsers => {
        console.error('AdminViewComponent.loadUsers(): error retrieving users');
        console.error(noUsers);
      }
    );
  }


   disableUser(userId: number) {
    this.userSvc.disable(userId).subscribe(
      data => {
        this.loadUsers();
      },
      error => {
        console.log("error disabling user through service")
      }
    );
  }

  enableUser(userId: number) {
    // console.log(userId);
    this.userSvc.enable(userId).subscribe(
      data => {
        this.loadUsers();
      },
      error => {
        console.log(error);
        console.log("error enabling user through service")
      }
    );
  }

  searchByUsername(username: string){
    this.userSvc.getAllUsers().subscribe(
      data => {
        data.forEach(element => {
          if(element.username === username){
            this.users.push(element);
          }
        });
      },
      noUsers => {
        console.error('AdminViewComponent.loadUsers(): error searching users');
        console.error(noUsers);
      }
    );
  }

}
