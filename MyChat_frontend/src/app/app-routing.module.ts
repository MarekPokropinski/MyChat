import { NgModule } from '@angular/core';
import { RouterModule, Routes, Router } from '@angular/router';
import { AuthenticationComponent } from './authentication/authentication.component'
import { RegisterComponent } from './register/register.component';
import { MainComponent } from './main/main.component';
import { RoomComponent } from './room/room.component';


const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: AuthenticationComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'chat', component: MainComponent },
  { path: 'room/:room', component: RoomComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
