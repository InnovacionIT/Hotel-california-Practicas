import { TestBed } from '@angular/core/testing';

import { ReservacionService } from './reservacion.service';

describe('ReservacionService', () => {
  let service: ReservacionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReservacionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
