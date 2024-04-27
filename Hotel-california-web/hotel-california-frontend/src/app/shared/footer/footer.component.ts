import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  constructor() { }

  scrollToTop(){
    window.scrollTo({
      top: 0,
      behavior: 'smooth'
    });
  }

  ngOnInit(): void {
  }

  nombreHotel = 'Hotel California';
  infoHotel = 'Descubre nuestro hotel con spa: relajación, tratamientos rejuvenecedores y una experiencia inolvidable. ¡Reserva ahora y déjate consentir! Comunícate con nosotros para más información.';
  direccionHotel = 'Calle Falsa 123';
  cpHotel = '4250';
  ciudadHotel = 'Villa Carlos Paz';
  provinciaHotel = 'Córdoba';
  paisHotel = 'Argentina';
  emailHotel = 'info@hotelcalifornia.com';
  telefonoHotel = '03564 - 4315000';
  instagramHotel = 'Hotel California';
  facebookHotel = 'Hotel California';
  twitterHotel = 'Hotel California';


}
