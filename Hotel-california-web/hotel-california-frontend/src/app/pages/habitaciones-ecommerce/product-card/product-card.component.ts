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

  public misServiciosPorHabitacion:Array<ServicioInterface>=[];
  public misHabitaciones:Array<Habitacion>=[];
  public startDate: string;
  public leaveDate: string;
  public selectedStartDate: string;
  public selectedLeaveDate: string;
  public usuarioId: number=1; // Reemplazar con el valor correspondiente, se inicializa en 1 para que no marque error

  constructor(private router: Router, private loginService:LoginService, private reservacionService: ReservacionService) {
  /*   this.misHabitaciones =[
      new Habitacion (1, `Habitación Single o Doble`, `Equipados con microondas, pava eléctrica y heladera tipo frigobar, ideales para prepararse una merienda o un snack (no aptos para comidas más elaboradas). TV-LED. Caja de seguridad en las habitaciones. Conexión a Internet inalámbrica (Wi-Fi).`, [`Baño privado`, `Tv LED`, `Microondas`, `Pava eléctrica`, `frigobar`, `Caja de Seguridad`, `Wi-fi`], true, 3500),
      new Habitacion (2, `Habitación Triple`, `Equipados con cómodos Kitchenette de 2 x 1,8 m. aprox. con microondas, pava eléctrica y heladera tipo frigobar, ideales para prepararse una merienda o un snack (no aptos para comidas más elaboradas). TV-LED. Caja de seguridad en las habitaciones. Conexión a Internet inalámbrica (Wi-Fi). Sommiers de una plaza como camas adicionales.`, [`Baño privado`, `Tv LED`, `Microondas`, `Pava eléctrica`, `frigobar`, `Caja de Seguridad`, `Wi-fi`], true, 3200),
      new Habitacion (3, `Habitación Cuádruple`, `Habitaciones familiares con 2 ambientes, dos camas doble plaza y 1 baño. Algunas con vistas al lago, TV 42 pulgadas con canales internacionales, minibar, etc.`, [`Baño privado`, `Tv LED`, `Microondas`, `Pava eléctrica`, `frigobar`, `Caja de Seguridad`, `Wi-fi`], true, 3000),
      new Habitacion (4, `Suite Exclusiva`, `Amplia y elegante habitación equipada con una cama King, muebles de madera oscura, obras de arte en las paredes, baño privado con artículos de aseo gratuitos, escritorio, frigobar, cafetera,   caja fuerte,  sofá cama y sillón de descanso. Esta habitación cuenta con un balcón privado con sillones y  un gran  jacuzzi   que le garantizan un ambiente ideal para un placentero descanso disfrutando la naturaleza circundante`, [`Baño privado`, `Tv LED`, `Microondas`, `Pava eléctrica`, `frigobar`, `Caja de Seguridad`, `Wi-fi`], true, 5500),
      new Habitacion (5, `Suite Junior Doble`, `Amplia habitación  equipada con dos camas queen. Disponen de muebles de madera oscura, obras de arte en las paredes, baño privado con artículos de aseo gratuitos, escritorio, frigobar,  caja fuerte, cafetera  terraza con sillones para disfrutar de la naturaleza con vista al jardín.`, [`Baño privado`, `Tv LED`, `Microondas`, `Pava eléctrica`, `frigobar`, `Caja de Seguridad`, `Wi-fi`], true, 5000)
    ]; */

    const today = new Date(); // Obtener fecha actual
    const sevenDaysLater = new Date(today.getTime() + 7 * 24 * 60 * 60 * 1000); // Sumar 7 días a la fecha actual

    this.startDate = today.toISOString().split('T')[0]; // Convertir fecha a formato YYYY-MM-DD
    this.leaveDate = sevenDaysLater.toISOString().split('T')[0]; // Convertir fecha a formato YYYY-MM-DD
    this.selectedStartDate = this.startDate;
    this.selectedLeaveDate = this.leaveDate;
  }

  ngOnInit(): void {
    this.startDate = this.formatDate(new Date()); // Inicializar con la fecha actual
    this.leaveDate = this.formatDate(this.calculateLeaveDate()); // Inicializar con la fecha de salida

    // Reemplaza con el valor correcto para usuarioId
    this.loginService.currentUserLoginOn.subscribe({
      next:(userLoginOn)=> {
        this.userLoginOn=userLoginOn;
      }
    });

    this.loginService.currentUserData.subscribe({
      next:(userData)=>{
      this.userData=userData;
      console.log("userData", userData);
      //this.userEmail = userData.usuario;
      this.userId=userData.usuarioId
      //this.userName = userData ? userData.nombre || '' : '';
      console.log("userId", this.userId);
      }
    })
    this.usuarioId = 1;
    this.reservacionService.getListadoHabitaciones().subscribe(
      habitaciones => {
        this.misHabitaciones = habitaciones;
        console.log("misHabitaciones", this.misHabitaciones);
        //new Habitacion (1, `Habitación Single o Doble`, `Equipados con microondas, pava eléctrica y heladera tipo frigobar, ideales para prepararse una merienda o un snack (no aptos para comidas más elaboradas). TV-LED. Caja de seguridad en las habitaciones. Conexión a Internet inalámbrica (Wi-Fi).`, [`Baño privado`, `Tv LED`, `Microondas`, `Pava eléctrica`, `frigobar`, `Caja de Seguridad`, `Wi-fi`], true, 3500),

      },
      error => {
        console.error('Error al obtener las habitaciones:', error);
      }
    );

   /*  this.reservacionService.getServiciosPorHabitacionId(habitacion.habitacionId).subscribe(
      servicios => {
        habitacion.servicios = servicios;
      },
      error => {
        console.error('Error al obtener los servicios:', error);
      }
    ); */


  }

  obtenerServicios(habitacion: Habitacion) {
    this.reservacionService.getServiciosPorHabitacionId(habitacion.habitacionId).subscribe(
      servicios => {
        habitacion.servicios = servicios;
      },
      error => {
        console.error('Error al obtener los servicios:', error);
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

  confirmReservation(habitacionId: number) {
    const usuarioId = this.usuarioId;
    const habitacion = this.misHabitaciones.find(h => h.habitacionId === habitacionId);
    const fechaReserva = new Date();

    const reserva = {
      usuarioId: usuarioId,
      habitacionId: habitacionId,
      fechaReserva: fechaReserva


    };

    this.createReservation(reserva);
    console.log('Reserva enviada:', reserva);

    this.mostrarInfo = false;//solo para simular
    this.mostrarBanner = true;
  }

  redirectToLogin (userLoginOn:boolean) {
    if (!userLoginOn){
      // Redireccionar al usuario a la ruta 'login'
    this.router.navigate(['login']);
    setTimeout(() => {
      window.location.reload();
    }, 0.001 );
    }
  }


  createReservation(reserva: any) {
    // Lógica para crear la reserva
    console.log('Creando reserva:', reserva);
      // Llamar a redirectPage() aquí
      console.log(this.userLoginOn);
    this.redirectToLogin(this.userLoginOn);
}


}
