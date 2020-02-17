import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IAddRequest, AddRequest } from 'app/shared/model/add-request.model';
import { AddRequestService } from './add-request.service';
import { IEvent } from 'app/shared/model/event.model';
import { EventService } from 'app/entities/event/event.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';

type SelectableEntity = IEvent | IUser;

@Component({
  selector: 'jhi-add-request-update',
  templateUrl: './add-request-update.component.html'
})
export class AddRequestUpdateComponent implements OnInit {
  isSaving = false;

  events: IEvent[] = [];

  users: IUser[] = [];

  editForm = this.fb.group({
    id: [],
    message: [],
    status: [null, [Validators.required]],
    eventId: [],
    userId: [],
    validatorId: []
  });

  constructor(
    protected addRequestService: AddRequestService,
    protected eventService: EventService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ addRequest }) => {
      this.updateForm(addRequest);

      this.eventService
        .query()
        .pipe(
          map((res: HttpResponse<IEvent[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IEvent[]) => (this.events = resBody));

      this.userService
        .query()
        .pipe(
          map((res: HttpResponse<IUser[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IUser[]) => (this.users = resBody));
    });
  }

  updateForm(addRequest: IAddRequest): void {
    this.editForm.patchValue({
      id: addRequest.id,
      message: addRequest.message,
      status: addRequest.status,
      eventId: addRequest.eventId,
      userId: addRequest.userId,
      validatorId: addRequest.validatorId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const addRequest = this.createFromForm();
    if (addRequest.id !== undefined) {
      this.subscribeToSaveResponse(this.addRequestService.update(addRequest));
    } else {
      this.subscribeToSaveResponse(this.addRequestService.create(addRequest));
    }
  }

  private createFromForm(): IAddRequest {
    return {
      ...new AddRequest(),
      id: this.editForm.get(['id'])!.value,
      message: this.editForm.get(['message'])!.value,
      status: this.editForm.get(['status'])!.value,
      eventId: this.editForm.get(['eventId'])!.value,
      userId: this.editForm.get(['userId'])!.value,
      validatorId: this.editForm.get(['validatorId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAddRequest>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
