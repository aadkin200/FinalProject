import { TestBed } from '@angular/core/testing';

import { TrailImageService } from './trail-image.service';

describe('TrailImageService', () => {
  let service: TrailImageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TrailImageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
