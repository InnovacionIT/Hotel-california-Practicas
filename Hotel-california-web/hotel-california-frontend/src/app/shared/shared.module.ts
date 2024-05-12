import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ButtonAppstoreComponent } from './components/button-appstore/button-appstore.component';





@NgModule({
  declarations: [
    HeaderComponent,
    FooterComponent,
    ButtonAppstoreComponent,
  ],
  imports: [
    BrowserModule,
    CommonModule,
    RouterModule,
    ReactiveFormsModule,
    HttpClientModule, 
    
  ],
  exports: [
    HeaderComponent,
    FooterComponent
  ]
})
export class SharedModule { }
