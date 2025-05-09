import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root' //Für die ganze anwendung erreichbar
})
export class APISService {
  private baseUrl: string = 'http://localhost:8000/api/v1/'; //Base URL für API

  constructor(private http: HttpClient) { }

  getData(endpoint: string): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}${endpoint}`); //holt data von der Server
  }

  postData(endpoint: string, data: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}${endpoint}`, data); //schickt data an den Server
  }
}
