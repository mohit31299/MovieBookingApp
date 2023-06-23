import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoginService } from 'src/app/service/login.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-forgot',
  templateUrl: './forgot.component.html',
  styleUrls: ['./forgot.component.css'],
})
export class ForgotComponent {
  forgotForm = {
    username: this.data.username,
    password: '',
    sercetAnswer: '',
  };

  secretQuestion = 'What is your pet name ?';

  constructor(
    private dialogRef: MatDialogRef<ForgotComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { username: String },
    private snack: MatSnackBar,
    private loginService: LoginService
  ) {}

  onSubmit() {
    if (
      this.forgotForm.username == null ||
      this.forgotForm.username.trim() == ''
    ) {
      this.snack.open('Username is required !!', '', {
        duration: 3000,
      });
      return;
    }

    if (
      this.forgotForm.password == null ||
      this.forgotForm.password.trim() == ''
    ) {
      this.snack.open('Password is required !!', '', {
        duration: 3000,
      });
      return;
    }

    if (
      this.forgotForm.sercetAnswer == null ||
      this.forgotForm.sercetAnswer.trim() == ''
    ) {
      this.snack.open('Secret answer is required !!', '', {
        duration: 3000,
      });
      return;
    }

    this.loginService.forgot(this.forgotForm).subscribe(
      (data: any) => {
        if (data != null && data.httpMethod == 'NOT_FOUND') {
          Swal.fire('error', data.msg, 'error');
          return;
        }

        Swal.fire('Success', 'Password updated successfully!!', 'success');
      },
      (error: any) => {
        Swal.fire('Error', 'Invalid data!!', 'error');
      }
    );
    this.closeDialog();
  }

  closeDialog() {
    this.dialogRef.close();
  }
}
