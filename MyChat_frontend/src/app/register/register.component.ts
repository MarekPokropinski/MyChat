import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../authentication.service';
import { User } from '../user';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private auth: AuthenticationService, private router: Router) { }
  user: User = new User();
  error = false;
  ngOnInit() {
  }

  handleError(error: HttpErrorResponse) {
    this.error = true;
    return throwError(
      'Something bad happened; please try again later.');
  }

  handleRegisterButtonClick(): void {
    this.auth.register(this.user).pipe(catchError((error) => this.handleError(error))).subscribe(() => {
      this.router.navigateByUrl('/login');
    })
  }
}
