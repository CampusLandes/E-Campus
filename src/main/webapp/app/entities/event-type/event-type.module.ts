import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ECampusSharedModule } from 'app/shared/shared.module';
import { EventTypeComponent } from './event-type.component';
import { EventTypeDetailComponent } from './event-type-detail.component';
import { EventTypeUpdateComponent } from './event-type-update.component';
import { EventTypeDeleteDialogComponent } from './event-type-delete-dialog.component';
import { eventTypeRoute } from './event-type.route';

@NgModule({
  imports: [ECampusSharedModule, RouterModule.forChild(eventTypeRoute)],
  declarations: [EventTypeComponent, EventTypeDetailComponent, EventTypeUpdateComponent, EventTypeDeleteDialogComponent],
  entryComponents: [EventTypeDeleteDialogComponent]
})
export class ECampusEventTypeModule {}
