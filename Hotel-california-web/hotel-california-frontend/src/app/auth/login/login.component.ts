import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { LoginRequest } from 'src/app/services/loginRequest';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm=this.formBuilder.group({
    usuario:['', [Validators.required, Validators.email]],
    password:['' ,Validators.required]
  })


constructor(private formBuilder:FormBuilder, private router:Router, private loginService:LoginService) { }

  ngOnInit(): void {
  }
  get usuario(){
    return this.loginForm.controls.usuario;
  }
  get password() {
    return this.loginForm.controls.password;
  }
  login(){
    if(this.loginForm.valid){
      this.loginService.login(this.loginForm.value as LoginRequest).subscribe({
        next:(userData)=> {
          console.info('login completo');
          console.log("userDataLogin", userData);
          if(userData.is_staff || userData.is_superuser){
            window.location.href = 'http://localhost:8000/admin/';
          }
          else{
            this.router.navigateByUrl('/nosotros');
          }
        },

        error:(errorData) => {
          console.error(errorData);

        },
        complete: () => {
          this.loginForm.reset();
        }
      });

    }
    else{
      this.loginForm.markAllAsTouched();
      alert('error al ingresar los datos')
    }
  }
}

