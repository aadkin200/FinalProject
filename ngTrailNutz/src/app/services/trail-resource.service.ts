import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Trail } from '../models/trail';
import { TrailResource } from '../models/trail-resource';
import { User } from '../models/user';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TrailResourceService {

  private url = environment.baseUrl + 'api/trail/{trialId}/comment';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
    })
  };

  constructor(
      private http: HttpClient
  ) { }

  create(trailResrc: TrailResource): Observable<TrailResource> {
    trailResrc.user = new User();
    trailResrc.resourceUrl = '';
    trailResrc.createdAt = '';
    trailResrc.title = '';
    trailResrc.enabled = true;
    trailResrc.trail = new Trail();
    return this.http.post<Comment>(this.url, trailResrc, this.httpOptions).pipe(
      catchError((err: any) => {
        console.error('TrailResourceService.create(): error creating TrailResource');
        return throwError(err);
      })
    );
  }

  update(trailResrc: TrailResource): Observable<TrailResource> {
    return this.http.put<TrailResource>(`${this.url}/${trailResrc.id}`, trailResrc, this.httpOptions).pipe(
      catchError((err: any) => {
        console.error('TrailResourceService.update(): error updating TrailResource');
        return throwError(err);
      })
    );
  }

  disable(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`, this.httpOptions).pipe(
      catchError((err: any) => {
        console.error('CommentService.disable(): error disabling comment');
        return throwError(err);
      })
    );
  }
}
