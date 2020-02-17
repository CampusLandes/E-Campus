import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAddRequest } from 'app/shared/model/add-request.model';
import { AddRequestService } from './add-request.service';

@Component({
  templateUrl: './add-request-delete-dialog.component.html'
})
export class AddRequestDeleteDialogComponent {
  addRequest?: IAddRequest;

  constructor(
    protected addRequestService: AddRequestService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.addRequestService.delete(id).subscribe(() => {
      this.eventManager.broadcast('addRequestListModification');
      this.activeModal.close();
    });
  }
}
