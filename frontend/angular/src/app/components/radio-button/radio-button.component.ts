import { Component, Input, Output, OnInit, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-radio-button',
  templateUrl: './radio-button.component.html',
  styleUrls: ['./radio-button.component.css']
})
export class RadioButtonComponent implements OnInit {

  @Input() isChecked?: boolean; 
  @Input() label?: string;
  @Input() id?: string; 
  @Input() name?: string;
  @Output() onChecked: EventEmitter<String> = new EventEmitter();
  constructor() { }

  ngOnInit(): void {
  }

  onClick() {
    this.isChecked = true;
    this.onChecked.emit(this.label);
  }

}
