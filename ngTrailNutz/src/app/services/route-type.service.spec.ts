import { TestBed } from '@angular/core/testing';

import { RouteTypeService } from './route-type.service';

describe('RouteTypeService', () => {
  let service: RouteTypeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RouteTypeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
