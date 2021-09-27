import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class HttpUserService {

  constructor(private http: HttpClient) { }

  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  getUserById(id: number): Observable<User> {
    return this.http.get<User> ('http://localhost:8081/users/' + id,{responseType: "json", headers: this.headers} );
  }
}
