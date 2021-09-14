import { Component, Input, OnInit, Output , EventEmitter} from '@angular/core';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css']
})
export class ModalComponent implements OnInit {
  @Input() modalTitle?: string;
  @Input() modalBody? : string; 
  @Input() modalButtonText?: string; 
  @Output() onSaveChanges = new EventEmitter();
  constructor() { }

  ngOnInit(): void {
  }
  callSaveChanges(){
    this.onSaveChanges.emit();
  }
}
