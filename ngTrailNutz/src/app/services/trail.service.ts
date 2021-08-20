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
  url = this.baseUrl + "api/trail";

  constructor(private http: HttpClient,
              private auth: AuthService
              ) { }

  index(): Observable<Trail[]> {
    return this.http.get<Trail[]>(`${this.baseUrl}trail`).pipe(
      catchError((err: any) => {
        console.error('trailService.index() error');
        return throwError(err);
      })

    );
  }

  public show(trailId: any) {
    return this.http.get<Trail>(`${this.baseUrl}trail/${trailId}`)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('error in trail.service.ts show()');
      })
    );
  }

  public create(trail: Trail) {
    return this.http.post<Trail>(`${this.url}`, trail, this.getHttpOptions())
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('error in trail.service.ts create()');
      })
    )
  }

  public update(trail: Trail) {
    return this.http.put<Trail>(`${this.url}/${trail.id}`, trail, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('error in trail.service.ts update()');
      })
    )
  }

  public destroy(id: number) {
    return this.http.put<Trail>(`${this.url}/${id}`, this.getHttpOptions()).pipe(
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
