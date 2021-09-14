import { Component, OnInit, Output , EventEmitter, Input} from '@angular/core';

@Component({
  selector: 'app-switch-button',
  templateUrl: './switch-button.component.html',
  styleUrls: ['./switch-button.component.css']
})
export class SwitchButtonComponent implements OnInit {

  @Input() isActive?: boolean;
  @Output() onChecked = new EventEmitter;
  constructor() { }

  ngOnInit(): void {
  }
  onInputChange(){
    console.log(this.isActive)
  }

  emitInputValue(){
    return this.onChecked.emit(this.isActive);
  }
}
