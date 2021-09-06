import { Component, OnInit } from '@angular/core';
import { ButtonComponent } from 'src/app/components/button/button.component';
import { faTimes } from '@fortawesome/free-solid-svg-icons';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Term } from 'src/app/models/Term';
import { Observable } from 'rxjs';

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

  constructor(private httpClient: HttpClient) { }

  private apiUrl = 'http://localhost:8080'; 
  ngOnInit(): void {
    this.getTerms();
  }

  onClick(){
    this.termButtonClicked = true
  }

  getAdmin() {
    const id = localStorage.getItem('id');
    const httpOptions = {
      headers: new HttpHeaders({
      'Content-Type': 'application/json',
      //authenticate user with basic auth 
      'Authorization': 'Basic ' + btoa('63589915196:password')
    })
  }
    return this.httpClient.get(this.apiUrl + '/api/users', httpOptions)
  }
  
  addTerm(term: Term){ 
    const httpOptiions = new HttpHeaders({
      'Content-Tupe': 'application/json',
      'Authorization':'Basic ' + btoa('63589915196:password')
    })
    // const term = new Term(this.termName, this.startDate, this.endDate);
    this.httpClient.post(this.apiUrl + '/api/terms', term, {headers: httpOptiions}).subscribe(term => this.terms.push(term));
  }

  getTerms() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json', 
        'Authorization': 'Basic ' + btoa('63589915196:password')
      })
    }
    return this.httpClient.get<Term []>(this.apiUrl + '/api/terms', httpOptions).subscribe(terms => this.terms = terms);
  }
  
}
