import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDialogModule } from '@angular/material/dialog';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatMenuModule } from '@angular/material/menu';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatToolbarModule } from '@angular/material/toolbar';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { LoginComponent } from './login.component';

fdescribe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LoginComponent],
      imports: [
        BrowserModule,
        BrowserAnimationsModule,
        MatCardModule,
        ReactiveFormsModule,
        MatFormFieldModule,
        MatInputModule,
        MatDialogModule,
        FormsModule,
        MatSnackBarModule,
        HttpClientModule,
      ],
    });
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.debugElement.componentInstance;
    fixture.detectChanges();
  });

  fit('should create', () => {
    expect(component).toBeTruthy();
  });

  fit("Should render form content",()=>{
    let formComponent =fixture.debugElement.nativeElement;
    expect(formComponent.querySelector('form').textContent).toContain("UsernamePassword");
  })

  fit('Checking validation for form fields on false value', () => {
    component.loginForm.setValue({
      "userName": "", 
      "password": "", 
    });

    expect(component.loginForm.valid).toEqual(false);
  });

  fit('Checking validation for form fields on true value', () => {
    component.loginForm.setValue({
      "userName": "test123", 
      "password": "123", 
    });

    expect(component.loginForm.valid).toEqual(true);
  });

});
