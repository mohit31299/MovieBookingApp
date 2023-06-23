import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddMovieComponent } from './components/addMovie/add-movie/add-movie.component';
import { MovieDetailComponent } from './components/dialog/movie-detail/movie-detail.component';
import { ErrorComponent } from './components/error/error/error.component';
import { HomeComponent } from './components/home/home/home.component';
import { LoginComponent } from './components/login/login/login.component';
import { MovieComponent } from './components/movie/movie/movie.component';
import { ProfileComponent } from './components/profile/profile/profile.component';
import { RegisterComponent } from './components/register/register/register.component';
import { AdminGuard } from './service/admin.guard';
import { AuthGuard } from './service/login.guard';


const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'register',
    component: RegisterComponent,
  },
  {
    canActivate:[AuthGuard],
    path: 'movie',
    component: MovieComponent,
  },
  {
    canActivate:[AuthGuard],
    path: 'profile',
    component: ProfileComponent,
  },
  {
    canActivate:[AuthGuard,AdminGuard],
    path: 'addMovie',
    component: AddMovieComponent,
  },
  {
    path: '**',
    component: ErrorComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
