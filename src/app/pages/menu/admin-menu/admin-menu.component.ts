import { Component, OnInit } from '@angular/core';
import { ButtonComponent } from 'src/app/components/button/button.component';
import { faTimes } from '@fortawesome/free-solid-svg-icons';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Term } from 'src/app/models/Term';
import { Observable } from 'rxjs';
import { TermService } from 'src/app/services/term.service';

@Component({
  selector: 'app-admin-menu',
  templateUrl: './admin-menu.component.html',
    styleUrls: ['./admin-menu.component.css']
})
export class AdminMenuComponent implements OnInit {

  termButtonClicked : boolean = false
  term: Term = {}; 
  terms: Term [] = []
  isTermActive: boolean = false; 
  faTimes = faTimes

  constructor(private httpClient: HttpClient, private termService: TermService) { }

  private apiUrl = 'http://localhost:8080'; 
  private headers = new HttpHeaders({
    'Content-Type':'applicatoin/json',
    'Authorization': 'Bearer ' + localStorage.getItem('token')
  })
  ngOnInit(): void {
    this.loadPage(); 
  }

  loadPage(){
    this.getAdmin();
    this.getTerms();
  }

  onClick(){
    this.termButtonClicked = true
  }

  getAdmin = () => this.termService.getAdmin().subscribe(resp => console.log(resp));;

  addTerm(term: Term){ 
    this.termService.addTerm(term).subscribe(
      term => this.terms.push(term)
      );
  }

  getTerms = () => this.termService.getTerms().subscribe(terms => this.terms = terms);
}
