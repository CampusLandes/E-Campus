import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ECampusSharedModule } from 'app/shared/shared.module';
import { EventComponent } from './event.component';
import { EventDetailComponent } from './event-detail.component';
import { EventUpdateComponent } from './event-update.component';
import { EventDeleteDialogComponent } from './event-delete-dialog.component';
import { FileUploadModule } from 'ng2-file-upload';
import { eventRoute } from './event.route';

@NgModule({
  imports: [ECampusSharedModule, RouterModule.forChild(eventRoute), FileUploadModule],
  declarations: [EventComponent, EventDetailComponent, EventUpdateComponent, EventDeleteDialogComponent],
  entryComponents: [EventDeleteDialogComponent]
})
export class ECampusEventModule {}
