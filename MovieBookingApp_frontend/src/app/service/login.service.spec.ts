import { TestBed } from '@angular/core/testing';

import { LoginService } from './login.service';

import {
  HttpClientTestingModule,
  HttpTestingController,
} from '@angular/common/http/testing';

fdescribe('LoginService', () => {
  let service: LoginService;

  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],

      providers: [LoginService],
    });

    service = TestBed.inject(LoginService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  fit('should be created', () => {
    expect(service).toBeTruthy();
  });

  fit('should send a POST request to login', () => {
    const login = { userName: 'John123', password: '123' };

    const response = 'Success';

    service.login(login).subscribe((res) => {
      expect(res).toEqual(response);
    });

    const req = httpMock.expectOne(
      'http://localhost:8081/api/v1/movieBooking/login'
    );

    expect(req.request.method).toBe('POST');

    req.flush(response);
  });

 
  fit('should send a Put request to forgot', () => {
    const forgot = { username: 'John123', password: '123', sercetAnswer: 'Tom' };

    const response = 'Success';

    service.forgot(forgot).subscribe((res:any) => {
      expect(res).toEqual(response);
    });

    const req = httpMock.expectOne(
      'http://localhost:8082/api/v1/movieBooking/forgotPassword'
    );

    expect(req.request.method).toBe('PUT');

    req.flush(response);
  });

});
