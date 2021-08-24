import { Component, OnInit } from '@angular/core';
import { Trail } from 'src/app/models/trail';
import { TrailImage } from 'src/app/models/trail-image';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  trail: Trail  = new Trail();

  constructor(private auth: AuthService) { }

  ngOnInit(): void {
  }

  loggedIn(): boolean {
    return this.auth.checkLogin();
  }

}
