import { Injectable } from '@angular/core';
import { TrainsInfo } from '../models/trains-info.model';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root' //Für die ganze anwendung erreichbar
})
export class APISService {
trainInfo: TrainsInfo = {
  total_trains: 0,
  total_passangers: 0,
  total_railways: 0
};
  playerId = 1

  constructor(http: HttpClient) {
    http.get<TrainsInfo>(`localhost:8080/getTrainInfo?playerId=${this.playerId}`).subscribe((trainInfo: TrainsInfo) => console.log(trainInfo))//TODO:

  }

}

