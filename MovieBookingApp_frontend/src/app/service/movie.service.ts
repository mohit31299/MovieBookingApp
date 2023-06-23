import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  url_user="http://localhost:8081/api/v1/movieBooking/user/";

  url_admin="http://localhost:8081/api/v1/movieBooking/admin/"

    // aws_url = "https://tew3whhuz6.execute-api.us-west-2.amazonaws.com/MovieBookingApp/moviebookingapp/"

  constructor(private http:HttpClient) { }

  getAllMovie(){
    return this.http.get(this.url_user+"searchAll");
    // return this.http.get(this.aws_url+"getall");
  }

  getMovie(movieName:any){
    return this.http.get(`${this.url_user}search/${movieName}`);
    // return this.http.get(`${this.aws_url}${movieName}`);
  }

  bookMovie(ticket:any){
    return this.http.post(this.url_user+"book",ticket,{responseType:"text"});
    // return this.http.post(this.aws_url+"book",ticket,{responseType:"text"});
  }

  deleteMovie(movieName:any){
    return this.http.delete(`${this.url_admin}delete/${movieName}`,{responseType:"text"});
    // return this.http.delete(`${this.aws_url}${movieName}`,{responseType:"text"});
  }

  addMovie(movie:any){
    return this.http.post(this.url_admin+"add",movie,{responseType:"text"});
    // return this.http.post(this.aws_url+"add",movie,{responseType:"text"});
  }
}
