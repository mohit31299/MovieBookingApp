import { TestBed } from '@angular/core/testing';

import { UserService } from './user.service';

import {
  HttpClientTestingModule,
  HttpTestingController,
} from '@angular/common/http/testing';

fdescribe('UserService', () => {
  let service: UserService;

  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],

      providers: [UserService],
    });

    service = TestBed.inject(UserService);

    httpMock = TestBed.inject(HttpTestingController);
  });

  fit('should be created', () => {
    expect(service).toBeTruthy();
  });

  fit('register should send a POST request to register', () => {
    const register = {
      username: 'Sample123',

      password: '123',

      role: 'ROLE_USER',

      email: 'sample@email.com',

      phoneNumber: '12345555',

      secretQuestion: 'What is your pet name ?',

      sercetAnswer: 'Tom',
    };

    service.register(register).subscribe((res) => {
      expect(res).toEqual(register);
    });

    const req = httpMock.expectOne(
      'http://localhost:8082/api/v1/movieBooking/register'
    );

    expect(req.request.method).toBe('POST');

    req.flush(register);
  });
});
