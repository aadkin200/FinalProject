import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrailFeedComponent } from './trail-feed.component';

describe('TrailFeedComponent', () => {
  let component: TrailFeedComponent;
  let fixture: ComponentFixture<TrailFeedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TrailFeedComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TrailFeedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
