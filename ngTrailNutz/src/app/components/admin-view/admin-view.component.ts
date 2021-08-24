import { Component, OnInit } from '@angular/core';
import { UrlSegment } from '@angular/router';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-admin-view',
  templateUrl: './admin-view.component.html',
  styleUrls: ['./admin-view.component.css']
})
export class AdminViewComponent implements OnInit {

  users:User[] = [];
  filteredUsers: User[] = [];
  user: User = new User();
  search: string = '';

  constructor(private userSvc: UserService) { }

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(){
    this.userSvc.getAllUsers().subscribe(
      data => {
        this.users = data;
        this.filteredUsers = this.users;
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

  searchByUsername(event: any){
    this.search = event.target.value;
    this.filteredUsers = this.users.filter(user =>
      user.username.toUpperCase().includes(this.search.toUpperCase())
      );
      this.users = this.filteredUsers;
      this.filteredUsers = this.users.map(x => x);
      this.search ='';
      if(!event.target.value){
        this.loadUsers();
      }
  }

}
