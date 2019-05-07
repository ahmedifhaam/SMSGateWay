import { TestBed } from '@angular/core/testing';

import { RegexentryService } from './regexentry.service';

describe('RegexentryService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RegexentryService = TestBed.get(RegexentryService);
    expect(service).toBeTruthy();
  });
});
