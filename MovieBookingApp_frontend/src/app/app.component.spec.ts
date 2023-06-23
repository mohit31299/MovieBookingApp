import { HttpClientModule } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatToolbarModule } from '@angular/material/toolbar';
import { RouterTestingModule } from '@angular/router/testing';
import { NgxUiLoaderHttpModule, NgxUiLoaderModule } from 'ngx-ui-loader';
import { AppComponent } from './app.component';
import { NavBarComponent } from './components/nav-bar/nav-bar/nav-bar.component';

fdescribe('AppComponent', () => {
  beforeEach(() =>
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        NgxUiLoaderModule,
        NgxUiLoaderHttpModule.forRoot({
          showForeground: true,
        }),
        HttpClientModule,
        MatToolbarModule,
        MatIconModule,
        MatMenuModule
      ],
      declarations: [AppComponent, NavBarComponent],
    })
  );

  fit('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  fit(`should have as title 'MovieBookingApp'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('MovieBookingApp');
  });

  
});
