export interface Reserva {
  tipo:string;
  descripcion:string;
  servicios:Array<any>;
  disponible:boolean;
}
export interface Reservation {
  reservaId: number;
  fechaReserva: Date;
  usuarioId: number;
  habitacionId: number;
  // Otras propiedades según la información necesaria de una reserva
}
export interface TipoHabitacionInterface {
  tipoHabitacionId: number;
  tipoHabitacion: string;
}
export interface HabitacionInterface {
  habitacionId: number;
  numero: number;
  piso: number;
  estado: string;
  precio: number;
  hotelId: number;
  tipoHabitacionId: number;
}
export interface ReservaInterface {
  reservaId: number;
  habitacionId: number;
  descripcion: string;
  fechaIngreso: Date;
  fechaEgreso: Date;
  usuarioId: number;
}
export interface ReservaPorHabitacionInterface {
  reservaHabitacionId: number;
  fechaIngreso: Date;
  fechaEgreso: Date;
  habitacionId: number;
  reservaId: number;
}
export interface ServicioInterface{
  habitacionId: number;
  sevicioId: number;
  servicio:string;
}
