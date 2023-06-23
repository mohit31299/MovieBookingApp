import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/service/login.service';
import { MovieService } from 'src/app/service/movie.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-movie',
  templateUrl: './add-movie.component.html',
  styleUrls: ['./add-movie.component.css'],
})
export class AddMovieComponent {
  addMovie = new FormGroup({
    movieName: new FormControl('', Validators.required),
    rating: new FormControl('', Validators.required),
    genre: new FormControl('', Validators.required),
    audio: new FormControl('', Validators.required),
    theatreName: new FormControl('', Validators.required),
    imgUrl: new FormControl('', Validators.required),
    totalNoseats: new FormControl(100),
    seatsAvailable: new FormControl(100),
  });

  constructor(
    private movieService: MovieService,
    private router: Router,
    private loginService: LoginService
  ) {}

  onSubmit() {
    this.movieService.addMovie(this.addMovie.value).subscribe(
      (data: any) => {
        if (data.httpMethod == 'FOUND') {
          Swal.fire('Found!', data.msg, 'warning');
        } else if (data == 'Not Authorized') {
          this.loginService.logOut();
          Swal.fire('error', 'Session expired!', 'error');
          this.router.navigate(['login']);
        } else {
          Swal.fire('Success', data, 'success');
        }
      },
      (error) => {
        Swal.fire('error', 'Unexpected error!', 'error');
       
      }
    );
    this.cancel();
  }

  cancel() {
    this.router.navigate(['movie']);
  }
}
