import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaderResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import {  map } from 'rxjs/operators';
import { Factura, detalle, detallePago, tipoPago } from './factura';


@Injectable({
  providedIn: 'root'
})
export class FacturaService {

  constructor( private http : HttpClient) { }
  baseUrl= 'http://localhost:8000/api/';
 
 //get 
 Factura(): Observable<Factura[]>{
  const url = `${this.baseUrl}/factura`;
  return this.http.get<Factura[]>(url);
 }
 detalle() {
  const url = `${this.baseUrl}/detalle`;
  return this.http.get<detalle[]>(url);
  }
  detallePago(): Observable <detallePago[]>{
    const url = `${this.baseUrl}/detallePago`;
  return this.http.get<detallePago[]>(url);
}
  tipoPago(): Observable <Object>{
  return this.http.get('././assets/hotel.json').pipe(
  map((tip : any) => tip.tipoPago)
)
    }

//post


  addFactura(factura:Factura): Observable<Factura[]>{
    const url = `${this.baseUrl}/factura`;
    return this.http.post<any>(url, factura);
  }    
  addDetalle(detalle: detalle): Observable<detalle[]>{
    const url = `${this.baseUrl}/detalle`;
    return this.http.post<detalle[]>(url,detalle);
  }
  addDetallePg(detallePago: detallePago){
    const url = `${this.baseUrl}/detallePago`;
    return this.http.post<any>(url, detallePago);
  }
  addTipoPago(tipoPago: tipoPago){
    const url = '././assets/hotel.json';
    return this.http.post<any>(url, this.tipoPago);
  }

  //put

  crearFactura(factura : Factura): Observable<Factura>{
    const url = `${this.baseUrl}/factura`
    return this.http.put<any>(url, factura);
  }
}