import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { catchError } from "rxjs/operators";
import { throwError } from "rxjs";

@Injectable({
  providedIn: 'root'
})

export class Routetype {

  url = environment.baseUrl + 'api/trails';


  constructor(
    private http: HttpClient
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
      const httpOptions = {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'X-Requested-With': 'XMLHttpRequest',
        }),
      };
      return httpOptions;
    }
  }

