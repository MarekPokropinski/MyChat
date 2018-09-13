import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from './user';
import { environment } from '../environments/environment';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})

export class AuthenticationService {
  private url = environment.url;
  private credentials: string;
  private username: string;

  constructor(private http: HttpClient) { }

  login(user: User) {
    this.credentials = 'Basic ' + btoa(user.username + ':' + user.password);
    const headers = new HttpHeaders({
      authorization: this.credentials
    });
    console.log('logging in');
    this.username = user.username;
    return this.http.get(this.url + '/user', { headers: headers });
  }
  getAuthentication(): HttpHeaders {
    return new HttpHeaders({
      authorization: this.credentials
    });
  }
  getUsername(): string {
    return this.username;
  }
  register(user: User) {
    return this.http.post(this.url + '/user/register', user);
  }
}
