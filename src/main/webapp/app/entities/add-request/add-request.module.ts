import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ECampusSharedModule } from 'app/shared/shared.module';
import { AddRequestComponent } from './add-request.component';
import { AddRequestDetailComponent } from './add-request-detail.component';
import { AddRequestUpdateComponent } from './add-request-update.component';
import { AddRequestDeleteDialogComponent } from './add-request-delete-dialog.component';
import { addRequestRoute } from './add-request.route';

@NgModule({
  imports: [ECampusSharedModule, RouterModule.forChild(addRequestRoute)],
  declarations: [AddRequestComponent, AddRequestDetailComponent, AddRequestUpdateComponent, AddRequestDeleteDialogComponent],
  entryComponents: [AddRequestDeleteDialogComponent]
})
export class ECampusAddRequestModule {}
