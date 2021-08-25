import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Amenity } from '../models/amenity';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AmenityService {

  url = environment.baseUrl;



  constructor(
    private http: HttpClient,
    private auth: AuthService

  ) { }



  show(): Observable<Amenity[]> {
  return this.http.get<Amenity[]>(`${this.url}api/traildetails/amenity`, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error('AmenityService.show(): error retrieving this amenity:');
        return throwError(err);
      })
    );
  }

  addAmenity(amenities: Amenity[], trailId: any) {
    return this.http.post<Amenity[]>(`${this.url}api/trail/${trailId}/amenity`, amenities, this.getHttpOptions())
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("error in amenity add");
      })
    )
  }

  getHttpOptions() {
    const credentials = this.auth.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-type': 'application/json',
        'X-Requested-with': 'XMLHttpRequest',
        'Authorization': `Basic ${credentials}`
      }),
    };
    return httpOptions
  }
}
