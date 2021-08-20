import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { catchError } from "rxjs/operators";
import { throwError } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class Amenity {

  url = environment.baseUrl + 'api/trails';



  constructor(
    private http: HttpClient
  ) { }



  show(): Observable<Amenity> {
    return this.http.get<Amenity>(`${this.url}`, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error('AmenityService.show(): error retrieving this amenity:');
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
