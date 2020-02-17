import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITripEvaluation } from 'app/shared/model/trip-evaluation.model';
import { TripEvaluationService } from './trip-evaluation.service';

@Component({
  templateUrl: './trip-evaluation-delete-dialog.component.html'
})
export class TripEvaluationDeleteDialogComponent {
  tripEvaluation?: ITripEvaluation;

  constructor(
    protected tripEvaluationService: TripEvaluationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tripEvaluationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tripEvaluationListModification');
      this.activeModal.close();
    });
  }
}
