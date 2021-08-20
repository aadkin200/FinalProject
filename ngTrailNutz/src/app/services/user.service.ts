import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private auth: AuthService, private http: HttpClient) { }

  baseUrl = environment.baseUrl;

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

  update(user:User):Observable<User>{
    return this.http.put<User>(`${this.baseUrl}/user/${user.id}`, user, this.getHttpOptions()).pipe(
       catchError((err:any) => {
         console.error(`User.update: error updating user`);
         return throwError(err);
       })
    )
  }
  disable(userId:number):Observable<User>{
    return this.http.delete<User>(`${this.baseUrl}/user/${userId}`, this.getHttpOptions()).pipe(
       catchError((err:any) => {
         console.error(`User.disable: error disabling user`);
         return throwError(err);
       })
    )
  }
  enable(userId:number):Observable<User>{
    return this.http.put<User>(`${this.baseUrl}/user/${userId}`, this.getHttpOptions()).pipe(
       catchError((err:any) => {
         console.error(`User.enable: error enabling user`);
         return throwError(err);
       })
    )
  }
}
