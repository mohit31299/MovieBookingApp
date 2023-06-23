import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/service/login.service';
import { MovieService } from 'src/app/service/movie.service';
import Swal from 'sweetalert2';
import { MovieDetailComponent } from '../movie-detail/movie-detail.component';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css'],
})
export class BookComponent {
  ticket = {
    movieName: this.data.movieName,
    bookedSeats: 0,
  };

  constructor(
    private dialogRef: MatDialogRef<MovieDetailComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { movieName: String; seats: number },
    private movieService: MovieService,
    private loginService: LoginService,
    private router: Router
  ) {}

  onSubmit() {
    if (this.ticket.bookedSeats <= 0) {
      Swal.fire('Invalid', 'Invalid value!', 'info');
    } else if (this.data.seats < this.ticket.bookedSeats) {
      Swal.fire('Invalid', this.data.seats + ' seats available only!', 'info');
    } else {
      this.movieService.bookMovie(this.ticket).subscribe(
        (data: any) => {
          if (data == 'Not Authorized') {
            this.loginService.logOut();
            Swal.fire('error', 'Session expired!', 'error');
            this.router.navigate(['login']);
          }
          Swal.fire('', data, 'success');
        },
        (error: any) => {
          Swal.fire('error', 'unexpected error', 'error');
        }
      );
    }
    this.closeDialog();
  }

  closeDialog() {
    this.dialogRef.close();
  }
}
