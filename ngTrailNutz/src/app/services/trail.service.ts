import { DatePipe } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Trail } from '../models/trail';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class TrailService {

  baseUrl = environment.baseUrl;

  constructor(private http: HttpClient,
              private datePipe: DatePipe,
              private auth: AuthService
    ) { }

  index(): Observable<Trail[]> {
    return this.http.get<Trail[]>(this.baseUrl, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error('rideService.index() error');
        return throwError(err);
      })
    );
  }

  public show(trailId: any) {
    return this.http.get<Trail>(`${this.baseUrl}/${trailId}`)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('error in trail.service.ts show()');
      })
    );
  }

  public create(trail: Trail) {
    return this.http.post<Trail>(this.baseUrl, trail, this.getHttpOptions())
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('error in trail.service.ts create()');
      })
    )
  }

  public update(trail: Trail) {
    return this.http.put<Trail>(`${this.baseUrl}/${trail.id}`, trail, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('error in trail.service.ts update()');
      })
    )
  }

  public destroy(id: number) {
    return this.http.put<Trail>(`${this.baseUrl}/${id}`, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('error deleting');
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
