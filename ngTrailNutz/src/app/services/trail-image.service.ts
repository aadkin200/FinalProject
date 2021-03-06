import { DatePipe } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { TrailImage } from '../models/trail-image';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class TrailImageService {

  baseUrl = environment.baseUrl;

  constructor(private http: HttpClient,
    private auth: AuthService
    ) { }

  addImage(trailImage: TrailImage, trailId: any) {
    // console.log(trailImage);
    delete(trailImage.trail);
    delete(trailImage.user)
    // console.log(trailImage);

    return this.http.post<TrailImage>(`${this.baseUrl}api/trail/${trailId}/trailimg`, trailImage, this.getHttpOptions())
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("error in trail-image.service.ts addImage()");
      })
    )
  }

  removeImage(trailImg: TrailImage, trailId: any) {
    return this.http.delete<TrailImage>(`${this.baseUrl}api/trail/${trailId}/trailimg/${trailImg.id}`, this.getHttpOptions())
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("error in trail-image.service.ts removeImage()");
      })
    )
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
