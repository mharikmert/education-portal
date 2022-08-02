import { Injectable } from "@angular/core";
import { MatDialog, MatDialogConfig, MatDialogRef, _closeDialogVia } from "@angular/material/dialog";
import { DialogComponent } from "src/app/components/dialog/dialog.component";

@Injectable()
export class Dialog {
    constructor(public dialog: MatDialog) { }
    dialogConfig = new MatDialogConfig()

    openDialog(data: any) {
        console.log(data);
        this.dialogConfig = {
            data: data,
            width: '500px',
            height: '300px',
            autoFocus: true,
        };
        this.dialog?.open(DialogComponent, this.dialogConfig);
    }

    closeDialog = () => this.dialog?.closeAll();
}