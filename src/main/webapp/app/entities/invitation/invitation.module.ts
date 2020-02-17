import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ECampusSharedModule } from 'app/shared/shared.module';
import { InvitationComponent } from './invitation.component';
import { InvitationDetailComponent } from './invitation-detail.component';
import { InvitationUpdateComponent } from './invitation-update.component';
import { InvitationDeleteDialogComponent } from './invitation-delete-dialog.component';
import { invitationRoute } from './invitation.route';

@NgModule({
  imports: [ECampusSharedModule, RouterModule.forChild(invitationRoute)],
  declarations: [InvitationComponent, InvitationDetailComponent, InvitationUpdateComponent, InvitationDeleteDialogComponent],
  entryComponents: [InvitationDeleteDialogComponent]
})
export class ECampusInvitationModule {}
