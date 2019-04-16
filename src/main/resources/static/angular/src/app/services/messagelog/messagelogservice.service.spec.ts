import { TestBed } from '@angular/core/testing';

import { MessagelogserviceService } from './messagelogservice.service';

describe('MessagelogserviceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MessagelogserviceService = TestBed.get(MessagelogserviceService);
    expect(service).toBeTruthy();
  });
});
