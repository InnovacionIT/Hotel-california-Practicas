import { Component, OnInit } from '@angular/core';
import { RegistroService } from '../../services/registro.service';
import { RegistroRequest } from 'src/app/services/registroRequest';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})

export class RegistroComponent implements OnInit {
  registroForm:FormGroup;
  cliente: RegistroRequest = {
    nombre: '',
    apellido: '',
    usuario: '',
    password: '',
    fechaDeNacimiento: '',
    telefono: '',
    ciudad: '',
  };

  constructor(private formBuilder:FormBuilder, 
    private registroService: RegistroService, 
    private router: Router) {
      this.registroForm=this.formBuilder.group(
        {
          nombre: ['', Validators.required],
          usuario: ['', [Validators.required, Validators.email]],
          password: ['', Validators.required],
          apellido: ['', Validators.required],
          ciudad: [''],
          fechaDeNacimiento: [''],
          telefono: ['']
        }
      )
  }

  ngOnInit(): void {}

  onSubmit(event: Event, cliente: RegistroRequest): void {
    event.preventDefault;

    if(this.registroForm.valid)
    {
        console.log("Enviado al servidor...")
        console.log(cliente)

        this.registroService.agregarUsuario(cliente as RegistroRequest).subscribe({
          next:()=> {
            alert("El registro ha sido creado correctamente. Por favor, inicia sesión");
            this.router.navigate(['/login'])
          },
          
          error:(errorData) => {
            console.error(errorData);
            
          }})
    }
    else{
      this.registroForm.markAllAsTouched();
    }
  };
    
}
