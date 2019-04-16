import { TestBed } from '@angular/core/testing';

import { MaskserviceService } from './maskservice.service';

describe('MaskserviceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MaskserviceService = TestBed.get(MaskserviceService);
    expect(service).toBeTruthy();
  });
});
