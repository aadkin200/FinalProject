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

  private url = environment.baseUrl + 'api/trail/{trialId}/comment';


  constructor(
    private auth: AuthService, private http: HttpClient
  ) { }

  create(comment: Comment): Observable<Comment> {
  comment.user = new User();
  comment.message = '';
  comment.createdAt = '';
  comment.updatedAt = '';
  comment.parentComment= 0;
  comment.replies = [];
  comment.enabled = true;
  comment.subject = '';
  comment.trail = new Trail();
    return this.http.post<Comment>(this.url, comment, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error('CommentService.create(): error creating comment');
        return throwError(err);
      })
    );
  }

  update(comment: Comment): Observable<Comment> {
    return this.http.put<Comment>(`${this.url}/${comment.id}`, comment, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error('CommentService.update(): error updating comment');
        return throwError(err);
      })
    );
  }

  disable(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`, this.getHttpOptions()).pipe(
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
