import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IInvitation, Invitation } from 'app/shared/model/invitation.model';
import { InvitationService } from './invitation.service';
import { IEvent } from 'app/shared/model/event.model';
import { EventService } from 'app/entities/event/event.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';

type SelectableEntity = IEvent | IUser;

@Component({
  selector: 'jhi-invitation-update',
  templateUrl: './invitation-update.component.html'
})
export class InvitationUpdateComponent implements OnInit {
  isSaving = false;

  events: IEvent[] = [];

  users: IUser[] = [];

  editForm = this.fb.group({
    id: [],
    message: [],
    status: [null, [Validators.required]],
    eventId: [],
    userId: []
  });

  constructor(
    protected invitationService: InvitationService,
    protected eventService: EventService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ invitation }) => {
      this.updateForm(invitation);

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

  updateForm(invitation: IInvitation): void {
    this.editForm.patchValue({
      id: invitation.id,
      message: invitation.message,
      status: invitation.status,
      eventId: invitation.eventId,
      userId: invitation.userId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const invitation = this.createFromForm();
    if (invitation.id !== undefined) {
      this.subscribeToSaveResponse(this.invitationService.update(invitation));
    } else {
      this.subscribeToSaveResponse(this.invitationService.create(invitation));
    }
  }

  private createFromForm(): IInvitation {
    return {
      ...new Invitation(),
      id: this.editForm.get(['id'])!.value,
      message: this.editForm.get(['message'])!.value,
      status: this.editForm.get(['status'])!.value,
      eventId: this.editForm.get(['eventId'])!.value,
      userId: this.editForm.get(['userId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInvitation>>): void {
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
