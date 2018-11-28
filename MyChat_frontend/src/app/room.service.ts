import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../environments/environment';
import { AuthenticationService } from './authentication.service';
import { Maybe } from "./maybe"

@Injectable({
  providedIn: 'root'
})
export class RoomService {
  private url = environment.url;
  constructor(private http: HttpClient, private auth: AuthenticationService) { }

  async getRoomMessages(roomName: string): Promise<Maybe<string[]>> {
    console.log(`${this.url}/room/messages?roomName=${roomName}`)
    const headers = this.auth.getAuthentication();
    if (headers !== null) {
      return Maybe.some(await this.http.get<string[]>(`${this.url}/room/messages?roomName=${roomName}`, { headers: headers }).toPromise());
    } else {
      return Maybe.none();
    }
  }
  async sendMessage(roomName: string, message: string) {
    const headers = this.auth.getAuthentication();
    await this.http.post(`${this.url}/room/send?roomName=${roomName}`, message, { headers: headers }).toPromise();
    console.log(`sending to ${this.url}/room/send?roomName=${roomName}`);
  }
}
