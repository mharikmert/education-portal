import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { ButtonComponent } from './components/button/button.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { LoginErrorComponent } from './components/login-error/login-error.component';
import { FormsModule } from '@angular/forms';
import { ParentMenuComponent } from './pages/menu/parent-menu/parent-menu.component';
import { AuthorizedMenuComponent } from './pages/menu/authorized-menu/authorized-menu.component';
import { StudentMenuComponent } from './pages/menu/student-menu/student-menu.component';
import { TeacherMenuComponent } from './pages/menu/teacher-menu/teacher-menu.component';
import { AdminMenuComponent } from './pages/menu/admin-menu/admin-menu.component';
import { LogoutButtonComponent } from './components/logout-button/logout-button.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { LogoutUseCase } from './common/usecase/logout-usecase';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    ButtonComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    LoginErrorComponent,
    ParentMenuComponent,
    AuthorizedMenuComponent,
    StudentMenuComponent,
    TeacherMenuComponent,
    AdminMenuComponent,
    LogoutButtonComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    FontAwesomeModule
  ],
  providers: [LogoutUseCase],
  bootstrap: [AppComponent]
})
export class AppModule { }
