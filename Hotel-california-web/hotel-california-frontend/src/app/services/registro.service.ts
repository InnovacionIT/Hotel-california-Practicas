import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RegistroRequest } from './registroRequest';

@Injectable({
  providedIn: 'root'
})
export class RegistroService {
  constructor(private http: HttpClient) { }
  // private url = 'assets/clientes.json';
  private url = 'http://localhost:8000/api/auth'


  agregarUsuario(data: RegistroRequest): Observable<RegistroRequest> {
    return this.http.post<RegistroRequest>(this.url + '/singup/', data);
  }

  postData(data:RegistroRequest): Observable<any>  {
    return this.http.post<any>(this.url, data );
  }
}
