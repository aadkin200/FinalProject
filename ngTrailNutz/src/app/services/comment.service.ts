import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models/user';
import { Comment } from '../models/comment';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Trail } from '../models/trail';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private url = environment.baseUrl + 'api/trail/{trialId}/comment';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
    })
  };

  constructor(
      private http: HttpClient
  ) { }

  create(comment: Comment): Observable<Comment> {
  comment.user = new User();
  comment.message = '';
  comment.createdAt = 0;
  comment.updatedAt = 0;
  comment.parentComment= 0;
  comment.replies = [];
  comment.enabled = true;
  comment.subject = '';
  comment.trail = new Trail();
    return this.http.post<Comment>(this.url, comment, this.httpOptions).pipe(
      catchError((err: any) => {
        console.error('CommentService.create(): error creating comment');
        return throwError(err);
      })
    );
  }

  update(comment: Comment): Observable<Comment> {
    return this.http.put<Comment>(`${this.url}/${comment.id}`, comment, this.httpOptions).pipe(
      catchError((err: any) => {
        console.error('CommentService.update(): error updating comment');
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
