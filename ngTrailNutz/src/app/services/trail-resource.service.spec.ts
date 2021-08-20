import { TestBed } from '@angular/core/testing';

import { TrailResourceService } from './trail-resource.service';

describe('TrailResourceService', () => {
  let service: TrailResourceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TrailResourceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
