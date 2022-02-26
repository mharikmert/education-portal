import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { AdminMenuComponent } from './pages/menu/admin-menu/admin-menu.component';
import { AuthorizedMenuComponent } from './pages/menu/authorized-menu/authorized-menu.component';
import { ParentMenuComponent } from './pages/menu/parent-menu/parent-menu.component';
import { StudentMenuComponent } from './pages/menu/student-menu/student-menu.component';
import { TeacherMenuComponent } from './pages/menu/teacher-menu/teacher-menu.component';
import { AuthGuard } from './auth.guard';
import { UserActionsComponent } from './pages/menu/authorized-menu/user-actions/user-actions.component';
import { ParametersComponent } from './pages/menu/authorized-menu/parameters/parameters.component';
import { CreateClassroomComponent } from './pages/menu/authorized-menu/create-classroom/create-classroom.component';
import { CreateLectureComponent } from './pages/menu/authorized-menu/create-lecture/create-lecture.component';
import { UserDetailsComponent } from './pages/menu/authorized-menu/user-actions/user-details/user-details.component';
import { AssignLectureComponent } from './pages/menu/authorized-menu/create-classroom/assign-lecture/assign-lecture.component';
import { AssingClassroomComponent } from './pages/menu/authorized-menu/user-actions/assing-classroom/assing-classroom.component';
import { ClassroomScheduleComponent } from './pages/menu/authorized-menu/create-classroom/classroom-schedule/classroom-schedule.component';
import { LoggedInComponent } from './pages/logged-in/logged-in.component';
import { TeacherRegistrationComponent } from './pages/teacher-registration/teacher-registration.component';
import { KvvkComponent } from './pages/kvkk/kvkk.component';

const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'home', component: HomeComponent}, 
  {path: 'teacher-register', component: TeacherRegistrationComponent },
  {path: 'kvkk', component: KvvkComponent },

    //refactor these as children routes  

    {path: 'admin-menu', component: AdminMenuComponent, canActivate: [AuthGuard]}, 
    {path: 'student-menu', component: StudentMenuComponent, canActivate: [AuthGuard]},
    {path: 'teacher-menu', component: TeacherMenuComponent, canActivate: [AuthGuard]}, 
    {path: 'parent-menu', component: ParentMenuComponent, canActivate: [AuthGuard]},
    {path: 'authorized-menu', component: AuthorizedMenuComponent, canActivate: [AuthGuard]},
    {path: 'user-actions', component: UserActionsComponent, canActivate: [AuthGuard]},
    {path: 'user-details', component: UserDetailsComponent, canActivate: [AuthGuard]},
    {path: 'create-lecture', component: CreateLectureComponent, canActivate: [AuthGuard]},
    {path: 'create-classroom', component: CreateClassroomComponent, canActivate: [AuthGuard]},
    {path: 'assign-lecture', component: AssignLectureComponent, canActivate: [AuthGuard]},
    {path: 'classroom-schedule', component: ClassroomScheduleComponent, canActivate: [AuthGuard]},
    {path: 'assign-classroom', component: AssingClassroomComponent, canActivate: [AuthGuard]},
    {path: 'parameters', component: ParametersComponent, canActivate: [AuthGuard]},
    {path: 'logged-in', component: LoggedInComponent, canActivate: [AuthGuard]}
  ]

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
