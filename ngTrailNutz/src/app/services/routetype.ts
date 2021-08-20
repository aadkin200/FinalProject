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

export class Routetype {

  url = environment.baseUrl + 'api/trails';


  constructor(
    private http: HttpClient,
    private auth: AuthService

  ) { }


  show(): Observable<Routetype> {
    return this.http.get<Routetype>(`${this.url}`, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error('RouteTypeService.show(): error retrieving this route:');
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

