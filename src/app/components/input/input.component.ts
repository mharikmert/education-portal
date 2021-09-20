import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.css']
})
export class InputComponent implements OnInit {

  @Input() inputType?: string; 
  @Input() label?: string; 
  @Output() onInput: EventEmitter<string> = new EventEmitter();
  
  inputValue?: string; 
  constructor() { }

  ngOnInit(): void {
  }
  emitInput(value : string | undefined){
    this.onInput.emit(this.inputValue)
  }

}
