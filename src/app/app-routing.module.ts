import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { AdminMenuComponent } from './pages/menu/admin-menu/admin-menu.component';
import { AuthorizedMenuComponent } from './pages/menu/authorized-menu/authorized-menu.component';
import { ParentMenuComponent } from './pages/menu/parent-menu/parent-menu.component';
import { StudentMenuComponent } from './pages/menu/student-menu/student-menu.component';
import { TeacherMenuComponent } from './pages/menu/teacher-menu/teacher-menu.component';
import { RegisterComponent } from './pages/register/register.component';
const routes: Routes = [
  {path: '', component : HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'student-menu', component: StudentMenuComponent},
  {path: 'teacher-menu', component: TeacherMenuComponent},
  {path: 'admin-menu', component: AdminMenuComponent},
  {path: 'parent-menu', component: ParentMenuComponent},
  {path: 'authorized-menu', component: AuthorizedMenuComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
