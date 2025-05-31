import { Injectable } from '@angular/core';
<<<<<<< HEAD
import { TrainsInfo } from '../models/trains-info.model';
import { HttpClient } from '@angular/common/http';

=======
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
>>>>>>> origin/Front_end

@Injectable({
  providedIn: 'root' //Für die ganze anwendung erreichbar
})
export class APISService {
<<<<<<< HEAD
trainInfo: TrainsInfo = {
  total_trains: 0,
  total_passangers: 0,
  total_railways: 0
};
  playerId = 1

  constructor(http: HttpClient) {
    http.get<TrainsInfo>(`localhost:8080/getTrainInfo?playerId=${this.playerId}`).subscribe((trainInfo: TrainsInfo) => console.log(trainInfo))//TODO:

  }

=======
  private baseUrl: string = 'http://localhost:8000/api/v1/'; //Base URL für API

  constructor(private http: HttpClient) { }

  getData(): Observable<string> {
    return this.http.get(this.baseUrl, { responseType: 'text' });
  }

  postData(endpoint: string, data: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}${endpoint}`, data); //schickt data an den Server
  }
>>>>>>> origin/Front_end
}

