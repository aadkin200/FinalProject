import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models/user';
import { Comment } from '../models/comment';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Trail } from '../models/trail';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private url = environment.baseUrl + 'api/trail/';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
    })
  };

  constructor(
      private http: HttpClient,
      private auth: AuthService
  ) { }

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


  create(comment: Comment, trailId:number): Observable<Comment> {
    return this.http.post<Comment>(`${this.url}${trailId}/comment`, comment, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error('CommentService.create(): error creating comment');
        return throwError(err);
      })
    );
  }

  update(comment: Comment, trailId:number): Observable<Comment> {
    return this.http.put<Comment>(`${this.url}${trailId}/comment`, comment, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error('CommentService.update(): error updating comment');
        return throwError(err);
      })
    );
  }

  getReply(commentId: number): Observable<Comment> {
    return this.http.get<Comment>(`${this.url}comment/${commentId}`, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error('CommentService.getReply(): error updating comment');
        return throwError(err);
      })
    );
  }

  disable(trailId:number, commentId: number): Observable<void> {
    return this.http.delete<void>(`${this.url}${trailId}/comment/${commentId}`, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error('CommentService.disable(): error disabling comment');
        return throwError(err);
      })
    );
  }




}
