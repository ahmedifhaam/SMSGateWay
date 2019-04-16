import { TestBed } from '@angular/core/testing';

import { SendsmsserviceService } from './sendsmsservice.service';

describe('SendsmsserviceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SendsmsserviceService = TestBed.get(SendsmsserviceService);
    expect(service).toBeTruthy();
  });
});
