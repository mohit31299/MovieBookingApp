import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatDividerModule} from '@angular/material/divider';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatCardModule} from '@angular/material/card';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { NavBarComponent } from './components/nav-bar/nav-bar/nav-bar.component';
import { LoginComponent } from './components/login/login/login.component';
import { HomeComponent } from './components/home/home/home.component';
import { RegisterComponent } from './components/register/register/register.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import { ErrorComponent } from './components/error/error/error.component';
import {MatDialogModule} from '@angular/material/dialog';
import { ForgotComponent } from './components/dialog/forgot/forgot.component';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { HttpClientModule } from '@angular/common/http';
import {MatMenuModule} from '@angular/material/menu';
import { MovieComponent } from './components/movie/movie/movie.component';
import { AuthInterceptor, authInterceptorProvider } from './service/auth.interceptor';
import { ProfileComponent } from './components/profile/profile/profile.component';
import { MovieDetailComponent } from './components/dialog/movie-detail/movie-detail.component';
import { BookComponent } from './components/dialog/book/book.component';
import { AddMovieComponent } from './components/addMovie/add-movie/add-movie.component';
import { NgxUiLoaderHttpModule, NgxUiLoaderModule } from "ngx-ui-loader";
import {MatTableModule} from '@angular/material/table';

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    LoginComponent,
    HomeComponent,
    RegisterComponent,
    ErrorComponent,
    ForgotComponent,
    MovieComponent,
    ProfileComponent,
    MovieDetailComponent,
    BookComponent,
    AddMovieComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatIconModule,
    MatDividerModule,
    MatToolbarModule,
    MatCardModule,
    MatProgressBarModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatDialogModule,
    FormsModule,
    MatSnackBarModule,
    HttpClientModule,
    MatMenuModule,
    NgxUiLoaderModule,
    NgxUiLoaderHttpModule.forRoot({
      showForeground:true
    }),
    MatTableModule
  ],
  providers: [
    authInterceptorProvider
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
