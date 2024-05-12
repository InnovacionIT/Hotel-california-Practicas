import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, forkJoin } from 'rxjs';
import { map, switchMap } from 'rxjs/operators';
import { ReservacionService } from '../../../services/reservacion.service';
import { LoginService } from 'src/app/services/login.service';
import { FacturaService } from 'src/app/services/factura.service';
import { User } from 'src/app/services/user';
import { ReservaInterface } from 'src/app/interface/reserva.interface';
import { Habitacion } from 'src/app/models/habitacion';
import { Detalle, Factura } from 'src/app/services/factura';

@Component({
  selector: 'app-reservas',
  templateUrl: './reservas.component.html',
  styleUrls: ['./reservas.component.css']
})

export class ReservasComponent implements OnInit {
  editarDatos : boolean = false;
  cambiosDatos : boolean = false; 
  misReservas: Array<ReservaInterface>=[];
  usuarioId: number = 0;
  userData?:User;
  habitaciones: Array<Habitacion> = [];

  constructor(
    private reservacionService: ReservacionService, 
    private facturaService: FacturaService,
    private loginService: LoginService, 
    private router : Router,
    ) { }

   ngOnInit(): void {
    
    // Traemos unfo del usuario logueado
    this.loginService.currentUserData.subscribe({
      next:(userData)=>{
        this.userData = userData;
        console.log("userData", userData);
        this.usuarioId = userData.usuarioId
        console.log("usuarioId", this.usuarioId);
      }
    })    

    // Obtenemos las reservas de este usuario
    this.getReservasUsuarioLogueado();
  }

  getReservasUsuarioLogueado(): void {
    this.reservacionService.getReservasUsuario(this.usuarioId).subscribe(
      reservas => {
        const habitacionesObservables = this.getHabitacionesReservadas(reservas);
        forkJoin(habitacionesObservables).subscribe(
          habitaciones => {
            this.misReservas = reservas.map((reserva, index) => {
              const habitacion = habitaciones[index];
              if (habitacion) {
                reserva.descripcion = habitacion.tipoHabitacion + " N° " + habitacion.numero;
              }
              return reserva;
              });
            }
          );
        }
      );
    }

    getHabitacionesReservadas(reservas: ReservaInterface[]): Observable<Habitacion>[] {
      const observables = [];
      // Obtenemos todas las habitaciones del usuario
      for (let reserva of reservas) {
        observables.push(this.reservacionService.getHabitacionPorId(reserva.habitacionId));
      }
      return observables;
    }

    reservaPagada(reserva: ReservaInterface): boolean {
      return false;
    }

    pagar(reserva: ReservaInterface): void {

    }

    cancelarReserva(reservaId: number) {
      this.reservacionService.eliminarReserva(reservaId).subscribe({
        next: (data) => {
            console.log(data)
            this.getReservasUsuarioLogueado();
            this.router.navigateByUrl('/reservas') 
          },
          error: (error) => {
            console.log(error)
          }
        });
    }
    
    mostrarForm(){
      this.editarDatos = true;
    }

    enviar(){
      this.editarDatos = false;
      this.cambiosDatos = true
      setTimeout(() => {
        this.router.navigate(['/nosotros'])}
        ,6000);
    }  
}
   