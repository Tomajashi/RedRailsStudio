import { Injectable } from '@angular/core';
import { TrainsInfo } from '../models/trains-info.model';
import { Resources } from '../models/resources.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';


@Injectable({
  providedIn: 'root' //Für die ganze anwendung erreichbar
})
export class APISService {
  private apiUrl = 'http://localhost:8080'; // URL to web api
  trainInfo: TrainsInfo = {
  total_trains: 0,
  total_passangers: 0,
  total_railways: 0
};
  playerId = 1

  constructor(private http: HttpClient) {
    http.get<TrainsInfo>(`localhost:8080/getTrainInfo?playerId=${this.playerId}`).subscribe((trainInfo: TrainsInfo) => console.log(trainInfo))//TODO:
    http.get<Resources>(`localhost:8080/getResources?playerId=${this.playerId}`).subscribe((resources: Resources) => console.log(resources))//TODO:
  }

  getTotalTrains(): Observable<number> {
  return this.http
    .get<TrainsInfo>(`${this.apiUrl}/getTrainInfo?playerId=${this.playerId}`).pipe(map((info: TrainsInfo) => info.total_trains));
  }

  getDBCoins(): Observable<number> {
  return this.http
    .get<Resources>(`${this.apiUrl}/getResources?playerId=${this.playerId}`).pipe(map((resources: Resources) => resources.DB_coin));
  }
}

