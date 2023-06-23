import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  url_movie = 'http://localhost:8081/api/v1/movieBooking/';
  url_user = 'http://localhost:8082/api/v1/movieBooking/';
  // aws_url = "https://tew3whhuz6.execute-api.us-west-2.amazonaws.com/MovieBookingApp/usermanagementapp";
  // aws_login_url = "https://tew3whhuz6.execute-api.us-west-2.amazonaws.com/MovieBookingApp/moviebookingapp";

  public loginStatusSubject = new Subject<boolean>();

  constructor(private http: HttpClient) {}

  login(user: any) {
    return this.http.post(this.url_movie + 'login', user);
    // return this.http.post(this.aws_login_url, user);
  }

  forgot(data: any): any {
    return this.http.put(this.url_user + 'forgotPassword', data);
    // return this.http.put(this.aws_url, data);
  }

  setToken(token: any) {
    localStorage.setItem('token', token.token);
  }

  logOut() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    this.loginStatusSubject.next(false);
  }

  getToken() {
    return localStorage.getItem('token');
  }

  isLoggedIn() {
    let token = this.getToken();
    if (token == undefined || token == null || token == '') {
      return false;
    } else {
      return true;
    }
  }

  getCurrentUser() {
    return this.http.get(this.url_user + 'current-user');
    // return this.http.get(this.aws_url);
  }

  setUser(user:any){
    localStorage.setItem("user",JSON.stringify(user));
  }

  getUser(){
     let user = localStorage.getItem("user")
     if(user != null){
       return JSON.parse(user);
     }return null;
  }

  getRole(){
    return this.getUser().role;
  }
}
