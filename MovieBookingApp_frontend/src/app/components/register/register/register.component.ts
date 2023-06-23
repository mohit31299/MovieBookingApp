import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/service/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  constructor(
    private snack: MatSnackBar,
    private registerService: UserService
  ) {}

  register = new FormGroup({
    username: new FormControl('', [Validators.required,Validators.pattern("^[a-zA-Z0-9]+$")]),
    fullName: new FormControl('', [Validators.required,Validators.pattern("^([A-Z]+[a-z]*[ ]{1}[A-Z]+[a-z]*)$")]),
    password: new FormControl('', [Validators.required,Validators.pattern("^[a-zA-Z0-9!@#$]+$")]),
    role: new FormControl('ROLE_USER'),
    email: new FormControl(),
    phoneNumber: new FormControl(),
    secretQuestion: new FormControl('What is your pet name ?'),
    sercetAnswer: new FormControl('',[ Validators.required,Validators.pattern("^([A-Z]+[a-z]*)$")]),
  });

  onSubmit() {
    if (
      this.register.value.username == null ||
      this.register.value.username.trim() == ''
    ) {
      this.snack.open('Username is required !!', '', {
        duration: 3000,
      });
      return;
    }

    if (
      this.register.value.fullName == null ||
      this.register.value.fullName.trim() == ''
    ) {
      this.snack.open('Full name is required !!', '', {
        duration: 3000,
      });
      return;
    }

    if (
      this.register.value.password == null ||
      this.register.value.password.trim() == ''
    ) {
      this.snack.open('Password is required !!', '', {
        duration: 3000,
      });
      return;
    }

    if (
      this.register.value.sercetAnswer == null ||
      this.register.value.sercetAnswer.trim() == ''
    ) {
      this.snack.open('Secret answer is required !!', '', {
        duration: 3000,
      });
      return;
    }

    this.registerService.register(this.register.value).subscribe(
      (user: any) => {
        if (user.httpMethod == 'FOUND') {
          Swal.fire('Warning', user.msg, 'warning');
          return;
        }

        Swal.fire(
          'Success',
          'Username ' +
            this.register.value.username +
            ' is successfully register!!',
          'success'
        );
      },
      (error: any) => {
        Swal.fire('error', error, 'error');
      }
    );
  }
}
