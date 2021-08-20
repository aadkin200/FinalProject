import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrailSinglePageComponent } from './trail-single-page.component';

describe('TrailSinglePageComponent', () => {
  let component: TrailSinglePageComponent;
  let fixture: ComponentFixture<TrailSinglePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TrailSinglePageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TrailSinglePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
