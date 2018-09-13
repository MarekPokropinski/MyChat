import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  constructor(private userService: UserService, private router: Router) { }
  user: string = 'abc';
  rooms: string[] = [];
  ngOnInit() {
    this.user = this.userService.getUsername();
    this.userService.getUsersRooms().subscribe(resp => {
      this.rooms = resp;
    },
      () => {
        this.router.navigateByUrl('/');
      })
  }
}
