import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/service/login.service';
import { MovieService } from 'src/app/service/movie.service';
import Swal from 'sweetalert2';
import { MovieDetailComponent } from '../../dialog/movie-detail/movie-detail.component';

@Component({
  selector: 'app-movie',
  templateUrl: './movie.component.html',
  styleUrls: ['./movie.component.css'],
})
export class MovieComponent implements OnInit {
  
  movie: any;

  roleStatus=false;

  constructor(private movieService: MovieService, private dialog: MatDialog,private loginService:LoginService,private router:Router) {
    
    
  }

  ngOnInit(): void {
   
    this.movieService.getAllMovie().subscribe(
      (movie: any) => {
        this.movie = movie;
        if(this.loginService.getRole() == "ROLE_ADMIN"){
          this.roleStatus = true;
        }
      },
      (error: any) => {
        Swal.fire("error","Session expired!","error");
        this.loginService.logOut();
        this.router.navigate(["login"]);
      }
    );
    
    
  }

  addMovie(){
    
    this.router.navigate(['addMovie']);
  }

  openDialog(movieName: String) {
    this.dialog.open(MovieDetailComponent, {
      data: { movieName: movieName },
    });
  }
}
