import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeComponent } from './home.component';

fdescribe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HomeComponent]
    });
    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.debugElement.componentInstance;
    fixture.detectChanges();
  });

  fit('should create', () => {
    expect(component).toBeTruthy();
  });

  fit("Should render h1 content",()=>{
    let elementComponent =fixture.debugElement.nativeElement;
    expect(elementComponent.querySelector('h1').textContent).toContain("Movie Booking");
  })
  
});
