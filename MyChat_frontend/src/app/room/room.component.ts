import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RoomService } from '../room.service';

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.css']
})
export class RoomComponent implements OnInit {

  constructor(private route: ActivatedRoute, private roomService: RoomService) { }

  roomName: string;
  messages: string[] = [];
  inputText: string;

  ngOnInit() {
    this.roomName = this.route.snapshot.paramMap.get('room');
    this.getMessages().then(() => { console.log(this.messages); });
  }

  async getMessages() {
    const messagesMaybe = await this.roomService.getRoomMessages(this.roomName);
    this.messages = messagesMaybe.getOrElse([]);
    console.log(this.messages);
  }
}
