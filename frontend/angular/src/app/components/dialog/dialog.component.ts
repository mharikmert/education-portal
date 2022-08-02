import { Component, EventEmitter, Inject, Input, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent {
  title?: string;
  content?: string;
  confirmationButtonText?: string;
  cancelButtonText?: string;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private dialogRef: MatDialogRef<DialogComponent>) {
    if (data) {
      this.content = data.content || this.content;
      this.title = data.title || this.title;
    }
    if (data.confirmationButtonText) {
      this.confirmationButtonText = data.confirmationButtonText || this.confirmationButtonText;
      this.cancelButtonText = data.cancelButtonText || this.cancelButtonText;
    }
  }

  ngOnInit(): void { }
  onClickClose() {
    this.dialogRef.close(true);
  }



}
