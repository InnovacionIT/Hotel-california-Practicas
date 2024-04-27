import { Component, OnInit,Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-banner',
  templateUrl: './banner.component.html',
  styleUrls: ['./banner.component.css']
})
export class BannerComponent implements OnInit {
  @Input() mostrarBanner: boolean = false;
  bannerError : boolean = false
  constructor(private router: Router) { }

  ngOnInit(): void {
    setTimeout(() => {
      this.router.navigate(['/reservas']);
      window.location.reload();
     }, 15000);
  }

  redirectToReservas(){
    this.router.navigate(['/reservas']);
    setTimeout(() => {
      window.location.reload();  
    }, 0.001 );
    

}
}