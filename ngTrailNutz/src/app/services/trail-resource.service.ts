import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Trail } from '../models/trail';
import { TrailResource } from '../models/trail-resource';
import { User } from '../models/user';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})

export class TrailResourceService {

  private url = environment.baseUrl;


  constructor(
    private auth: AuthService, private http: HttpClient
  ) { }

  create(trailResrc: TrailResource, trailId:number): Observable<TrailResource> {
    return this.http.post<TrailResource>(this.url + `api/trail/${trailId}/trailresources`, trailResrc, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error('TrailResourceService.create(): error creating TrailResource');
        return throwError(err);
      })
    );
  }

  update(trailResrc: TrailResource, trailId:number): Observable<TrailResource> {
    return this.http.put<TrailResource>(this.url + `api/trail/${trailId}/trailresources/${trailResrc.id}`, trailResrc, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error('TrailResourceService.update(): error updating TrailResource');
        return throwError(err);
      })
    );
  }

  disable(id: number, trailId:number): Observable<void> {
    return this.http.delete<void>(this.url + `api/trail/${trailId}/trailresources/${id}`, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error('CommentService.disable(): error disabling comment');
        return throwError(err);
      })
    );
  }

  getHttpOptions() {
    const credentials = this.auth.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'X-Requested-With': 'XMLHttpRequest',
        'Authorization': `Basic ${credentials}`
      }),
    };
    return httpOptions;
  }

}
