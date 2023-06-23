import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ForgotComponent } from '../../dialog/forgot/forgot.component';
import Swal from 'sweetalert2';
import { LoginService } from 'src/app/service/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  constructor(private dialog: MatDialog, 
              private snack: MatSnackBar,
              private loginService:LoginService,
              private router:Router) {}

  hide = true;

  user ={
    username:"",
    fullName:"",
    email:"",
    phoneNumber: 0,
    role:""
  }

  loginForm = new FormGroup({
    userName: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
  });

  onSubmit() {
    if (
      this.loginForm.value.userName == null ||
      this.loginForm.value.userName.trim() == ''
    ) {
      this.snack.open('Username is required !!', '', {
        duration: 3000,
      });
      return;
    }

    if (
      this.loginForm.value.password == null ||
      this.loginForm.value.password.trim() == ''
    ) {
      this.snack.open('Password is required !!', '', {
        duration: 3000,
      });
      return;
    }
    
    this.loginService.login(this.loginForm.value).subscribe(
      (token:any) =>{
        this.loginService.setToken(token);
        this.loginService.getCurrentUser().subscribe(
          (user:any)=>{
          
            this.user.username = user.username;
            this.user.fullName = user.fullName;
            this.user.phoneNumber = user.phoneNumber;
            this.user.email = user.email;
            this.user.role = user.role;
            this.loginService.setUser(this.user);
            
          }
        )
        this.loginService.loginStatusSubject.next(true);
        this.router.navigate(["movie"]);

        Swal.fire("Success","User logged in successfully!!","success");
        
      },
      (error:any)=>{
        Swal.fire("error","Invalid credentials try again!!","error")
        
      }
    );
    
  }

  openDialog() {
    this.dialog.open(ForgotComponent, {
      data: { username: this.loginForm.value.userName },
    });
  }
}
