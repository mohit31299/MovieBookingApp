import { TestBed } from '@angular/core/testing';

import { MovieService } from './movie.service';

import {
  HttpClientTestingModule,
  HttpTestingController,
} from '@angular/common/http/testing';

fdescribe('MovieService', () => {
  let service: MovieService;

  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],

      providers: [MovieService],
    });

    service = TestBed.inject(MovieService);

    httpMock = TestBed.inject(HttpTestingController);
  });

  fit('should be created', () => {
    expect(service).toBeTruthy();
  });

  fit('getAllMovies should send a Get request ', () => {
    const getAllMovies = [
      {
        movieName: 'sample1',

        theatreName: 'Test',

        totalNoseats: 100,

        seatsAvailable: 100,

        audio: 'English',

        genre: '',

        imgUrl: '',
      },

      {
        movieName: 'sample2',

        theatreName: 'test',

        totalNoseats: 100,

        seatsAvailable: 100,

        audio: 'English',

        genre: 'Nahi hai',

        imgUrl: '',
      },
    ];

    service.getAllMovie().subscribe((res) => {
      expect(res).toEqual(getAllMovies);
    });

    const req = httpMock.expectOne(
      'http://localhost:8081/api/v1/movieBooking/user/searchAll'
    );

    expect(req.request.method).toBe('GET');

    req.flush(getAllMovies);
  });

  fit('bookMovie should send a Post request ', () => {
    const bookMovie = { movieName: 'MovieSample', bookedSeats: 5 };
    const response = 'Success';

    service.bookMovie(bookMovie).subscribe((res) => {
      expect(res).toEqual(response);
    });

    const req = httpMock.expectOne(
      'http://localhost:8081/api/v1/movieBooking/user/book'
    );

    expect(req.request.method).toBe('POST');

    req.flush(response);
  });
});
