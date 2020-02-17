import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEventType } from 'app/shared/model/event-type.model';
import { EventTypeService } from './event-type.service';

@Component({
  templateUrl: './event-type-delete-dialog.component.html'
})
export class EventTypeDeleteDialogComponent {
  eventType?: IEventType;

  constructor(protected eventTypeService: EventTypeService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.eventTypeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('eventTypeListModification');
      this.activeModal.close();
    });
  }
}
