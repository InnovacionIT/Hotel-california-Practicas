import { Component, OnInit } from '@angular/core';
import { ReservacionService } from '../../../services/reservacion.service';
import { LoginService } from 'src/app/services/login.service';
import { Router } from '@angular/router';
import { User } from 'src/app/services/user';
import { ReservaInterface } from 'src/app/interface/reserva.interface';
import { Habitacion } from 'src/app/models/habitacion';
import { Observable, forkJoin } from 'rxjs';

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


    cancelarReserva(){
      // TODO
    }
    mostrarForm(){
      this.editarDatos = true;
    }
   enviar(){
    this.editarDatos = false;
    this.cambiosDatos = true
    setTimeout(() => {
      this.router.navigate(['/nosotros'])}
      ,6000);}
  
//     getFactura():void {
//       this.facturaService.Factura().subscribe(factura => {
//         console.log('datos de fatura', factura)
//       })
//     }
//     getDetalle():void {
//       this.facturaService.Factura().subscribe(factura => {
//         console.log('datos de fatura', factura)
//       })
//     }
//     getDetallePago():void {
//       this.facturaService.detalle().subscribe(detalle => {
//         console.log('detalle', detalle)
//       })
//     }
//     getTipoPago():void {
//       this.facturaService.tipoPago().subscribe(tipoPago => {
//         console.log('detalle',tipoPago )
//     })
//   }

//   actualizarFactura(factura:Factura){
//     this.facturaService.addFactura(factura).subscribe(factura => {
//       console.log('actualizando factura',factura)
//   })
//   }

//   actualizarDetalle(detalle: detalle){
//     this.facturaService.addDetalle(detalle).subscribe(detalle => {
//       console.log('actualizando detalles',detalle)
//   })
//   }
//   actualizarDetallePago(detallePago: detallePago){
//     this.facturaService.addDetallePg(detallePago).subscribe(detallePago => {
//       console.log('actualizando detalles de pago',detallePago)
//   })
//   } actualizarTipoPago(tipoPago: tipoPago){
//     this.facturaService.addTipoPago(tipoPago).subscribe(tipoPago => {
//       console.log('actualizando detalles de pago',tipoPago)
//   })
//   }

//   crearFactura(factura : Factura):void{
//     this.facturaService.crearFactura(factura).subscribe(factura => {
//       console.log('factura creada', factura);
//     })
// }

// //habitaciones

//       getListadoHabitaciones(): void {
//        this.reservacionService.getListadoHabitaciones().subscribe(habitaciones => {
//          console.log('Listado de habitaciones:', habitaciones);
//        });
//      }

//      getHabitacionPorId(roomId: number): void {
//        this.reservacionService.getHabitacionPorId(roomId).subscribe(detalle => {
//          console.log('Detalle de habitación:', detalle);
//        });
//      }

//      getReservaPorId(reservationId: number): void {
//        this.reservacionService.getReservaPorId(reservationId).subscribe(reserva => {
//         console.log('Reserva por ID:', reserva);
//        });
//      }

//     modificarReserva(reservationId: number, updatedData: any): void {
//       this.reservacionService.modificarReserva(reservationId, updatedData).subscribe(reserva => {
//         console.log('Reserva modificada:', reserva);
//       });
//     }

//     createReservation(reservationData: any): void {
//       this.reservacionService.createReservation(reservationData).subscribe(reserva => {
//         console.log('Reserva creada:', reserva);
//       });
//     }

//     eliminarReserva(reservationId: number): void {
//       this.reservacionService.eliminarReserva(reservationId).subscribe(() => {
//         console.log('Reserva eliminada');
//       });
//     }

//     verificarDisponibilidad(roomId: number, checkInDate: Date, checkOutDate: Date): void {
//       this.reservacionService.verificarDisponibilidad(roomId, checkInDate, checkOutDate).subscribe(disponible => {
//         console.log('Habitación disponible:', disponible);
//       });
//     }
}
   