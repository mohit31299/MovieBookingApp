import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css'],
})
export class NavBarComponent {
  isLoggedIn = false;
  primary="primary"
  accent="accent"

  constructor(public loginService: LoginService, private router: Router) {
    this.isLoggedIn = this.loginService.isLoggedIn();

    this.loginService.loginStatusSubject
      .asObservable()
      .subscribe((data: any) => {
        this.isLoggedIn = this.loginService.isLoggedIn();
      });
  }

  logout() {
    this.loginService.logOut();
  }
  
}
