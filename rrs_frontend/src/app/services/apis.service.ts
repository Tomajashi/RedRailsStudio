import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root' //Für die ganze anwendung erreichbar
})
export class APISService {
  private baseUrl: string = 'http://localhost:8000/api/v1/'; //Base URL für API

  constructor(private http: HttpClient) { }

  getData(): Observable<string> {
    return this.http.get(this.baseUrl, { responseType: 'text' });
  }

  postData(endpoint: string, data: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}${endpoint}`, data); //schickt data an den Server
  }
}
