import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Reservation, HabitacionInterface, ReservaInterface, } from '../interface/reserva.interface';
import { Habitacion } from '../models/habitacion';

@Injectable({
  providedIn: 'root'
})
export class ReservacionService {
  reservacionUrl = 'http://localhost:8000/api/';

  constructor(private http: HttpClient) { }

  //GET Habitaciones
  getListadoHabitaciones(): Observable<Habitacion[]> {
    const url = `${this.reservacionUrl}habitacion/`;
    return this.http.get<Habitacion[]>(url);
  }

  getHabitacionPorId(roomId: number): Observable<Habitacion> {
    const url = `${this.reservacionUrl}habitacion/${roomId}/`;
    return this.http.get<Habitacion>(url);
  }
  getHabitacionesDisponibles(): Observable<HabitacionInterface[]> {
    const url = `${this.reservacionUrl}habitacion/Disponible`;
    return this.http.get<HabitacionInterface[]>(url);
  }

  getServiciosPorHabitacionId(roomId: number): Observable<string[]> { // ver la interface de servicios
    const url = `${this.reservacionUrl}servicio/${roomId}`;
    return this.http.get<string[]>(url);
  }

  getReservas(): Observable<ReservaInterface[]> {
    return this.http.get<ReservaInterface[]>(this.reservacionUrl + "reserva/");
  }

  getReservaPorId(reservaId: number): Observable<ReservaInterface> {
    const url = `${this.reservacionUrl}reserva/${reservaId}/`;
    return this.http.get<ReservaInterface>(url);
  }

  getReservasUsuario(userId: number): Observable<ReservaInterface[]> {
    return this.http.get<ReservaInterface[]>(this.reservacionUrl + "reserva/user/" + userId + "/");
  }

   // PUT
  modificarReserva(reservaId: number, updatedData: Reservation): Observable<any> {
    const url = `${this.reservacionUrl}reserva/${reservaId}/`;
    return this.http.put<any>(url, updatedData);
  }
  //POST
  createReservation(reservationData: Reservation): Observable<any> {
    const url = `${this.reservacionUrl}reserva/`;
    return this.http.post<any>(url, reservationData);
  }

  //DELETE
  eliminarReserva(reservaId: number): Observable<any> {
    const url = `${this.reservacionUrl}reserva/${reservaId}`; 
    return this.http.delete<ReservaInterface>(url);
  }

  //verifica la disponibilidad de una habitación específica para un rango de fechas dado
  verificarDisponibilidad(roomId: number, checkInDate: Date, checkOutDate: Date): Observable<boolean> {
    const url = `${this.reservacionUrl}/habitaciones/${roomId}/disponibilidad`;
    const params = {
      checkInDate: checkInDate.toISOString(),
      checkOutDate: checkOutDate.toISOString()
    };
    return this.http.get<boolean>(url, { params });
  }

}
