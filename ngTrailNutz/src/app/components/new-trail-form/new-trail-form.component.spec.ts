import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewTrailFormComponent } from './new-trail-form.component';

describe('NewTrailFormComponent', () => {
  let component: NewTrailFormComponent;
  let fixture: ComponentFixture<NewTrailFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewTrailFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewTrailFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
