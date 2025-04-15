import { TestBed } from '@angular/core/testing';

import { APISService } from './apis.service';

describe('APISService', () => {
  let service: APISService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(APISService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
