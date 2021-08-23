import { Component, OnInit } from '@angular/core';
import { Trail } from 'src/app/models/trail';
import { TrailImage } from 'src/app/models/trail-image';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  trail: Trail  = new Trail();

  constructor() { }

  ngOnInit(): void {
  }

}
