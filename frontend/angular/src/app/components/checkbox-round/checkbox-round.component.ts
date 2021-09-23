import { Component, Input, OnInit, Output, EventEmitter} from '@angular/core';

@Component({
  selector: 'app-checkbox-round',
  templateUrl: './checkbox-round.component.html',
  styleUrls: ['./checkbox-round.component.css']
})
export class CheckboxRoundComponent implements OnInit {
  
  isChecked: boolean = false;
  @Input() label?: string;
  @Input() checkedId?: string; 
  @Input() name?: string;
  @Output() onChecked: EventEmitter<boolean> = new EventEmitter();
  constructor() { }

  ngOnInit(): void {}

  emitChecked(value: boolean){
    console.log(value)
    this.onChecked.emit(this.isChecked);
  }
}
