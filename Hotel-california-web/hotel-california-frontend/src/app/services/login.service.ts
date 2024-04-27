import { Injectable } from '@angular/core';
import { LoginRequest } from './loginRequest';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject, tap } from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  currentUserLoginOn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  currentUserData: BehaviorSubject<User> = new BehaviorSubject<User>({ usuarioId: 0, usuario: '', is_staff: false, is_superuser: false });

  constructor(private http: HttpClient) {}

  login(_credentials: LoginRequest): Observable<User> {
    return this.http.post<User>('http://localhost:8000/api/auth/login/', _credentials).pipe(
      tap(userData => {
        this.currentUserData.next(userData);
        console.log("userDataLserv", userData);
        this.currentUserLoginOn.next(true);
      })
    );
  }

  get userData(): Observable<User> {
    return this.currentUserData.asObservable();
  }

  get userLoginOn(): Observable<boolean> {
    return this.currentUserLoginOn.asObservable();
  }

  logout(): void {
    this.currentUserData.next({ usuarioId: 0, usuario: '', is_staff: false, is_superuser: false });
    this.currentUserLoginOn.next(false);
  }
}
