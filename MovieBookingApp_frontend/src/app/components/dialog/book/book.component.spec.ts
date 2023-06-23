import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import {
  MatDialog,
  MatDialogModule,
  MatDialogRef,
  MAT_DIALOG_DATA,
} from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { BookComponent } from './book.component';

fdescribe('BookComponent', () => {
  let component: BookComponent;
  let fixture: ComponentFixture<BookComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BookComponent],
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
      providers: [
        {
          provide: MatDialogRef,
          useValue: [],
        },
        {
          provide: MAT_DIALOG_DATA,
          useValue: [],
        },
      ],
    });
    fixture = TestBed.createComponent(BookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  fit('should create', () => {
    expect(component).toBeTruthy();
  });

  fit("Should render form content",()=>{
    let formComponent =fixture.debugElement.nativeElement;
    expect(formComponent.querySelector('form').textContent).toContain("SeatsSubmitCancel");
  })

  fit('Should get values from form fields', () => {
    
    component.ticket.movieName='test'
    component.ticket.bookedSeats=10

    expect(component.ticket.movieName).toEqual("test");
    expect(component.ticket.bookedSeats).toEqual(10);
  });
});
