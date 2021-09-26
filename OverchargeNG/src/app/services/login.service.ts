import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  loggedIn: boolean = false;
  

  constructor(private http: HttpClient) { }

  private postHeaders = new HttpHeaders({ 'Access-Control-Allow-Credentials': 'true' });

  login(username: String, password: String): Observable<User> {
    return this.http.post<User>(`http://localhost:8081/login`,{
      'username': username,
      'password': password
    }, {
      withCredentials: true
    }, );
  }

  

  getUsername(): string {
    let username = localStorage.getItem("username");

    if (username == '' || username == "Guest" || username == undefined) {
      localStorage.setItem("username", "Guest");
      this.loggedIn = false;
      return "Guest";
    } else {
      return username;
    }
  }

  logout(){
    this.http.post(`http://localhost:8081/logout`, "");
  }

  setUsername(username: string, role: number): void {
    if(username != "Guest") {
      this.loggedIn = true;
    }
    localStorage.setItem("username", username);
    localStorage.setItem("role", "" + role)
  }
}
