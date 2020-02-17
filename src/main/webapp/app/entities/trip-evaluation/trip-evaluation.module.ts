import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ECampusSharedModule } from 'app/shared/shared.module';
import { TripEvaluationComponent } from './trip-evaluation.component';
import { TripEvaluationDetailComponent } from './trip-evaluation-detail.component';
import { TripEvaluationUpdateComponent } from './trip-evaluation-update.component';
import { TripEvaluationDeleteDialogComponent } from './trip-evaluation-delete-dialog.component';
import { tripEvaluationRoute } from './trip-evaluation.route';

@NgModule({
  imports: [ECampusSharedModule, RouterModule.forChild(tripEvaluationRoute)],
  declarations: [
    TripEvaluationComponent,
    TripEvaluationDetailComponent,
    TripEvaluationUpdateComponent,
    TripEvaluationDeleteDialogComponent
  ],
  entryComponents: [TripEvaluationDeleteDialogComponent]
})
export class ECampusTripEvaluationModule {}
