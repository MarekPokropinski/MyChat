import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../authentication.service';
import { User } from '../user';
import { Router } from '@angular/router';


@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.css']
})
export class AuthenticationComponent implements OnInit {

  constructor(private auth: AuthenticationService, private router: Router) { }

  user: User = new User();

  ngOnInit() {
  }
  handleLoginButtonClick(): void {
    this.auth.login(this.user).subscribe(
      () => {
        this.router.navigateByUrl('/chat');
      },
      () => {
        console.log("wrong username or password");
      })
  }
}
