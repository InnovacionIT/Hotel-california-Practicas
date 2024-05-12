import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
//import { Reserva } from '../../../interface/reserva.interface';
import { Habitacion } from '../../../models/habitacion';
import { ReservasComponent } from '../../vista-interna/reservas/reservas.component'; // Importa ReservasComponent
import { ReservacionService } from '../../../services/reservacion.service';
import { LoginService } from 'src/app/services/login.service';
import { User } from 'src/app/services/user';
import { Reserva, Reservation, TipoHabitacionInterface, HabitacionInterface, ReservaInterface, ReservaPorHabitacionInterface, ServicioInterface } from 'src/app/interface/reserva.interface';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css']
})
export class ProductCardComponent implements OnInit {
  mostrarBanner: boolean = false;
  mostrarInfo: boolean = true; //solo para simular despues borrar
  userLoginOn:boolean=false;
  userData?:User;
  userId:number=0;
  selectedRoom!:Habitacion;
  public misServiciosPorHabitacion:Array<ServicioInterface>=[];
  public misHabitaciones:Array<Habitacion>=[];
  public startDate: string;
  public leaveDate: string;
  public selectedStartDate: string;
  public selectedLeaveDate: string;
  public usuarioId: number=1; // Reemplazar con el valor correspondiente, se inicializa en 1 para que no marque error
  user!:User;
  constructor(private router: Router, private loginService:LoginService, private reservacionService: ReservacionService) {
    const today = new Date(); // Obtener fecha actual
    const sevenDaysLater = new Date(today.getTime() + 7 * 24 * 60 * 60 * 1000); // Sumar 7 días a la fecha actual
    this.startDate = today.toISOString().split('T')[0]; // Convertir fecha a formato YYYY-MM-DD
    this.leaveDate = sevenDaysLater.toISOString().split('T')[0]; // Convertir fecha a formato YYYY-MM-DD
    this.selectedStartDate = this.startDate;
    this.selectedLeaveDate = this.leaveDate;
  }
  servicios!: any[]

  public imagenesPorHabitacion: { [key: number]: string } = {
    1: '../../../../assets/img/habitaciones/doble.jpg',
    2: '../../../../assets/img/habitaciones/doble.jpg',
    3: '../../../../assets/img/habitaciones/suiteDoble.jpg',
    4: '../../../../assets/img/habitaciones/single.jpg',
    5: '../../../../assets/img/habitaciones/suiteDoble.jpg',
    6: '../../../../assets/img/habitaciones/single.jpg',
    7: '../../../../assets/img/habitaciones/single.jpg',
    8: '../../../../assets/img/habitaciones/suite.jpg'
  };


  ngOnInit(): void {
    this.loginService.userData.subscribe({
      next: (user) => {
        this.user = user;
      }
    });
    this.loginService.userLoginOn.subscribe({
      next: (userLogged) => {
        this.userLoginOn = userLogged;
      }
    })
    this.startDate = this.formatDate(new Date()); // Inicializar con la fecha actual
    this.leaveDate = this.formatDate(this.calculateLeaveDate()); // Inicializar con la fecha de salida

    this.reservacionService.getListadoHabitaciones().subscribe({
      next: (habitaciones:Habitacion[]) => {
        this.misHabitaciones = habitaciones;
        this.misHabitaciones.forEach(hab => {
          this.obtenerServicios(hab);
        })
        console.log("misHabitaciones", this.misHabitaciones);
      },
      error: (error) => {
        console.error('Error al obtener las habitaciones:', error);
      }
    }
  );

}

  calculateLeaveDate(): Date {
    const startDate = new Date(this.startDate);
    const leaveDate = new Date(startDate);
    leaveDate.setDate(leaveDate.getDate() + 7); // Agregar 7 días a la fecha de inicio
    return leaveDate;
  }

  formatDate(date: Date): string {
    // Lógica para formatear la fecha en el formato deseado (YYYY-MM-DD)
    return `${date.getFullYear()}-${(date.getMonth() + 1)
      .toString()
      .padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}`;
  }

  getDateDifference(): number {
    const start = new Date(this.startDate);
    const leave = new Date(this.leaveDate);
    const diff = Math.abs(leave.getTime() - start.getTime());
    const diffDays = Math.ceil(diff / (1000 * 60 * 60 * 24));
    return diffDays;
  }

  obtenerServicios(habitacion: Habitacion) {
    this.reservacionService.getServiciosPorHabitacionId(habitacion.habitacionId).subscribe(
      servicios => {
        habitacion.servicios = servicios;
        console.log(servicios);
      },
      error => {
        console.error('Error al obtener los servicios:', error);
      }
    );
  }


  confirmReservation(habitacionId: number) {
    if(!this.user || !this.userLoginOn){
      this.redirectToLogin();
      return;
    }
    const usuarioId = this.user.usuarioId;
    const habitacion = this.misHabitaciones.find(h => h.habitacionId === habitacionId);
    const fechaReserva = new Date().toISOString().split('T')[0];

    const reserva = {
      usuarioId: usuarioId,
      habitacionId: habitacionId,
      fechaReserva: fechaReserva,
      fechaIngreso: this.selectedStartDate,
      fechaEgreso: this.selectedLeaveDate


    };

    this.createReservation(reserva);
    
    this.mostrarInfo = false;//solo para simular
  }

  redirectToLogin () {
    this.router.navigateByUrl('/login');
  }


createReservation(reserva: any) {
    console.log('Creando reserva:', reserva);
    this.reservacionService.createReservation(reserva).subscribe({
      next: (data) => {
        console.log(data)
        console.log('Reserva enviada:', reserva);
        this.router.navigateByUrl('/reservas') 
      },
      error: (error) => {
        console.log(error)
      }
    });
}


}
