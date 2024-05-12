import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Reserva, Reservation, TipoHabitacionInterface, HabitacionInterface, ReservaInterface, ReservaPorHabitacionInterface, ServicioInterface } from '../interface/reserva.interface';
import { Habitacion } from '../models/habitacion';

@Injectable({
  providedIn: 'root'
})
export class ReservacionService {
  reservacionUrl = 'http://localhost:8000/api/';

  constructor(private http: HttpClient) { }

  //GET

  getListadoHabitaciones(): Observable<Habitacion[]> {
    const url = `${this.reservacionUrl}habitacion/`;
    return this.http.get<Habitacion[]>(url);
  }

  getHabitacionPorId(roomId: number): Observable<HabitacionInterface> {
    const url = `${this.reservacionUrl}habitacion/${roomId}/`;
    return this.http.get<HabitacionInterface>(url);
  }
  getHabitacionesDisponibles(): Observable<HabitacionInterface[]> {
    const url = `${this.reservacionUrl}habitacion/Disponible`;
    return this.http.get<HabitacionInterface[]>(url);
  }

  getServiciosPorHabitacionId(roomId: number): Observable<string[]> { // ver la interface de servicios
    const url = `${this.reservacionUrl}servicio/${roomId}`;
    return this.http.get<string[]>(url);
  }

  getReservas(): Observable<ReservaInterface> {
    const url = `${this.reservacionUrl}reserva`; // Reemplaza 'reservas' y '${reservaId}' con las URL correspondientes en tu backend
    return this.http.get<ReservaInterface>(url);
  }

  getReservaPorId(reservaId: number): Observable<ReservaInterface> {
    const url = `${this.reservacionUrl}reserva/${reservaId}/`; // Reemplaza 'reservas' y '${reservaId}' con las URL correspondientes en tu backend
    return this.http.get<ReservaInterface>(url);
  }

 /*  getReservasPorHabitacion(roomId: number): Observable<ReservaInterface[]> {
    const url = `${this.reservacionUrl}/reservas/habitacion/${roomId}`;
    return this.http.get<ReservaInterface[]>(url);
  } */

/*   getReservaPorCliente(clientId: number): Observable<ReservaInterface[]> {
    const url = `${this.reservacionUrl}/reservas/cliente/${clientId}`;
    return this.http.get<ReservaInterface[]>(url);
  } */


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
    const url = `${this.reservacionUrl}reserva/${reservaId}`; // Reemplaza 'reservas' y '${reservaId}' con las URL correspondientes en tu backend
    return this.http.delete<any>(url);
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
