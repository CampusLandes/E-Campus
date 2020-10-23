import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EventTypeService } from 'app/entities/event-type/event-type.service';
import { IEventType, EventType } from 'app/shared/model/event-type.model';
import { JhiAlertService } from 'ng-jhipster';
import { NzModalRef } from 'ng-zorro-antd';
import { Observable } from 'rxjs';

@Component({
  selector: 'jhi-type-add',
  templateUrl: './type-add.component.html',
  styleUrls: ['./type-add.component.scss']
})
export class TypeAddComponent {
  isSaving: Boolean = false;
  validateForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    protected eventTypeService: EventTypeService,
    private modal: NzModalRef,
    private alertService: JhiAlertService
  ) {
    this.validateForm = this.fb.group({
      name: ['', [Validators.required]]
    });
  }

  submitForm(): void {
    this.isSaving = true;
    const eventType = this.createFromForm();
    if (eventType.id !== undefined) {
      this.subscribeToSaveResponse(this.eventTypeService.update(eventType));
    } else {
      this.subscribeToSaveResponse(this.eventTypeService.create(eventType));
    }
  }

  private createFromForm(): IEventType {
    return {
      ...new EventType(),
      name: this.validateForm.get(['name'])!.value
    };
  }

  resetForm(e: MouseEvent): void {
    e.preventDefault();
    this.validateForm.reset();
    this.modal.destroy();
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEventType>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      (e: HttpErrorResponse) => this.onSaveError(e)
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.modal.destroy();
  }

  protected onSaveError(e: HttpErrorResponse): void {
    this.isSaving = false;
    this.alertService.error('error.http.' + e.status);
  }
}
