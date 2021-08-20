import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { catchError } from "rxjs/operators";
import { throwError } from "rxjs";
import { AuthService } from './auth.service';



@Injectable({
  providedIn: 'root'
})
export class Difficulty {

  url = environment.baseUrl + 'api/trails';



  constructor(
    private http: HttpClient,
    private auth: AuthService

  ) { }


  show(): Observable<Difficulty> {
    return this.http.get<Difficulty>(`${this.url}`, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error('DifficultyService.show(): error retrieving this difficulty:');
        return throwError(err);
      })
    );
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
