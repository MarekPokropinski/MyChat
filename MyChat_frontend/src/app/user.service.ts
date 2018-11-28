import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../environments/environment';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private url = environment.url;
  constructor(private http: HttpClient, private auth: AuthenticationService) { }

  getUsersRooms() {
    const headers = this.auth.getAuthentication();
    if (headers !== null) {
      return this.http.get<string[]>(this.url + '/user/rooms?username=' + this.auth.getUsername(), { headers: headers });
    } else {
      return null;
    }
  }
  getUsername(): string {
    return this.auth.getUsername();
  }
}
