import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ButtonComponent } from './components/button/button.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { LoginErrorComponent } from './components/login-error/login-error.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ParentMenuComponent } from './pages/menu/parent-menu/parent-menu.component';
import { AuthorizedMenuComponent } from './pages/menu/authorized-menu/authorized-menu.component';
import { StudentMenuComponent } from './pages/menu/student-menu/student-menu.component';
import { TeacherMenuComponent } from './pages/menu/teacher-menu/teacher-menu.component';
import { AdminMenuComponent } from './pages/menu/admin-menu/admin-menu.component';
import { LogoutButtonComponent } from './components/logout-button/logout-button.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { LogoutUseCase } from './common/usecase/logout-usecase';
import { SwitchButtonComponent } from './components/switch-button/switch-button.component';
import { ModalComponent } from './components/modal/modal.component';
import { UserActionsComponent } from './pages/menu/authorized-menu/user-actions/user-actions.component';
import { CreateLectureComponent } from './pages/menu/authorized-menu/create-lecture/create-lecture.component';
import { CreateClassroomComponent } from './pages/menu/authorized-menu/create-classroom/create-classroom.component';
import { ParametersComponent } from './pages/menu/authorized-menu/parameters/parameters.component';
import { CheckboxRoundComponent } from './components/checkbox-round/checkbox-round.component';
import { UserDetailsComponent } from './pages/menu/authorized-menu/user-actions/user-details/user-details.component';
import { InputComponent } from './components/input/input.component';
import { AssignLectureComponent } from './pages/menu/authorized-menu/create-classroom/assign-lecture/assign-lecture.component';
import { AssingClassroomComponent } from './pages/menu/authorized-menu/user-actions/assing-classroom/assing-classroom.component';
import { ClassroomScheduleComponent } from './pages/menu/authorized-menu/create-classroom/classroom-schedule/classroom-schedule.component';
import { LoggedInComponent } from './pages/logged-in/logged-in.component';
import { TeacherRegistrationComponent } from './pages/teacher-registration/teacher-registration.component';
import { RadioButtonComponent } from './components/radio-button/radio-button.component';
import { StudentRegistrationComponent } from './pages/student-registration/student-registration.component';

@NgModule({
  declarations: [
    AppComponent,
    ButtonComponent,
    HomeComponent,
    LoginComponent,
    LoginErrorComponent,
    ParentMenuComponent,
    AuthorizedMenuComponent,
    StudentMenuComponent,
    TeacherMenuComponent,
    AdminMenuComponent,
    LogoutButtonComponent,
    SwitchButtonComponent,
    ModalComponent,
    UserActionsComponent,
    CreateLectureComponent,
    CreateClassroomComponent,
    ParametersComponent,
    CheckboxRoundComponent,
    UserDetailsComponent,
    InputComponent,
    AssignLectureComponent,
    AssingClassroomComponent,
    ClassroomScheduleComponent,
    LoggedInComponent,
    TeacherRegistrationComponent,
    RadioButtonComponent,
    StudentRegistrationComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    FontAwesomeModule
  ],
  providers: [LogoutUseCase],
  bootstrap: [AppComponent]
})
export class AppModule { }
