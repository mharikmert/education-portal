import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {
  constructor() { }

  ngOnInit(): void { }

  @Input() columns: string[] = [];
  @Input() data: any
  columnsToDisplay: string[] = this.columns.slice();

}

