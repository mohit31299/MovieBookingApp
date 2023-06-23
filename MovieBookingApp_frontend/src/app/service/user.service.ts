import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  url = 'http://localhost:8082/api/v1/movieBooking/';
  // url = "https://tew3whhuz6.execute-api.us-west-2.amazonaws.com/MovieBookingApp/usermanagementapp"

  constructor(private http: HttpClient) {}

  register(user: any) {
    return this.http.post(this.url + 'register', user);
    // return this.http.post(this.url , user);
  }
}
