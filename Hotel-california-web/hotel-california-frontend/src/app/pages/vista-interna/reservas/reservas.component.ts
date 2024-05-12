import { Component, OnInit, ViewChild,ViewContainerRef, } from '@angular/core';
import { FacturaService } from 'src/app/services/factura.service';
import { Factura, detalle, detallePago, tipoPago } from 'src/app/services/factura';
import { ReservacionService } from '../../../services/reservacion.service';
import { raceWith } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-reservas',
  templateUrl: './reservas.component.html',
  styleUrls: ['./reservas.component.css']
})
export class ReservasComponent implements OnInit {
  editarReserva : boolean = true;
  mostrarReserva : boolean = true;
  Editar : boolean = false;
  editarDatos : boolean = true;
  cambiosDatos : boolean = false;
  monto = 19200;

  constructor(
    private facturaService: FacturaService,
    private reservacionService: ReservacionService, 
    private router : Router,
    ) { }
  factura!: number;
  habitacion!: number ; // Valor de ejemplo para habitacionId
  user!: number; // Valor de ejemplo para usuarioId
  reservation!: number ; // Valor de ejemplo para reservaId

   ngOnInit(): void {
    
  }


    getFactura():void {
      this.facturaService.Factura().subscribe(factura => {
        console.log('datos de fatura', factura)
      })
    }
    getDetalle():void {
      this.facturaService.Factura().subscribe(factura => {
        console.log('datos de fatura', factura)
      })
    }
    getDetallePago():void {
      this.facturaService.detalle().subscribe(detalle => {
        console.log('detalle', detalle)
      })
    }
    getTipoPago():void {
      this.facturaService.tipoPago().subscribe(tipoPago => {
        console.log('detalle',tipoPago )
    })
  }

  actualizarFactura(factura:Factura){
    this.facturaService.addFactura(factura).subscribe(factura => {
      console.log('actualizando factura',factura)
  })
  }

  actualizarDetalle(detalle: detalle){
    this.facturaService.addDetalle(detalle).subscribe(detalle => {
      console.log('actualizando detalles',detalle)
  })
  }
  actualizarDetallePago(detallePago: detallePago){
    this.facturaService.addDetallePg(detallePago).subscribe(detallePago => {
      console.log('actualizando detalles de pago',detallePago)
  })
  } actualizarTipoPago(tipoPago: tipoPago){
    this.facturaService.addTipoPago(tipoPago).subscribe(tipoPago => {
      console.log('actualizando detalles de pago',tipoPago)
  })
  }

  crearFactura(factura : Factura):void{
    this.facturaService.crearFactura(factura).subscribe(factura => {
      console.log('factura creada', factura);
    })
}

//habitaciones

      getListadoHabitaciones(): void {
       this.reservacionService.getListadoHabitaciones().subscribe(habitaciones => {
         console.log('Listado de habitaciones:', habitaciones);
       });
     }

     getHabitacionPorId(roomId: number): void {
       this.reservacionService.getHabitacionPorId(roomId).subscribe(detalle => {
         console.log('Detalle de habitación:', detalle);
       });
     }

     getReservaPorId(reservationId: number): void {
       this.reservacionService.getReservaPorId(reservationId).subscribe(reserva => {
        console.log('Reserva por ID:', reserva);
       });
     }

    /* getReservaPorHabitacion(roomId: number): void {
      this.reservacionService.getReservasPorHabitacion(roomId).subscribe(reservas => {
        console.log('Reservas por habitación:', reservas);
      });
    } */

    /* getReservaPorCliente(clientId: number): void {
      this.reservacionService.getReservaPorCliente(clientId).subscribe(reservas => {
        console.log('Reservas por cliente:', reservas);
      });
    } */

    modificarReserva(reservationId: number, updatedData: any): void {
      this.reservacionService.modificarReserva(reservationId, updatedData).subscribe(reserva => {
        console.log('Reserva modificada:', reserva);
      });
    }

    createReservation(reservationData: any): void {
      this.reservacionService.createReservation(reservationData).subscribe(reserva => {
        console.log('Reserva creada:', reserva);
      });
    }

    eliminarReserva(reservationId: number): void {
      this.reservacionService.eliminarReserva(reservationId).subscribe(() => {
        console.log('Reserva eliminada');
      });
    }

    verificarDisponibilidad(roomId: number, checkInDate: Date, checkOutDate: Date): void {
      this.reservacionService.verificarDisponibilidad(roomId, checkInDate, checkOutDate).subscribe(disponible => {
        console.log('Habitación disponible:', disponible);
      });
    }
    
    CancelarReserva(){
      this.mostrarReserva = false;
    }
    mostrarForm(){
      this.Editar = true;
      this.editarDatos = false
    }
   enviar(){
    this.editarDatos = false;
    this.Editar = false;
    this.mostrarReserva = false;
    this.cambiosDatos = true
    setTimeout(() => {
      this.router.navigate(['/nosotros'])}
      ,6000);
  }
}
   