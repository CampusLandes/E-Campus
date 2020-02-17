import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IMessage, Message } from 'app/shared/model/message.model';
import { MessageService } from './message.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { IConversation } from 'app/shared/model/conversation.model';
import { ConversationService } from 'app/entities/conversation/conversation.service';

type SelectableEntity = IUser | IConversation;

@Component({
  selector: 'jhi-message-update',
  templateUrl: './message-update.component.html'
})
export class MessageUpdateComponent implements OnInit {
  isSaving = false;

  users: IUser[] = [];

  conversations: IConversation[] = [];

  editForm = this.fb.group({
    id: [],
    message: [null, [Validators.required]],
    sawPeople: [],
    conversationId: []
  });

  constructor(
    protected messageService: MessageService,
    protected userService: UserService,
    protected conversationService: ConversationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ message }) => {
      this.updateForm(message);

      this.userService
        .query()
        .pipe(
          map((res: HttpResponse<IUser[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IUser[]) => (this.users = resBody));

      this.conversationService
        .query()
        .pipe(
          map((res: HttpResponse<IConversation[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IConversation[]) => (this.conversations = resBody));
    });
  }

  updateForm(message: IMessage): void {
    this.editForm.patchValue({
      id: message.id,
      message: message.message,
      sawPeople: message.sawPeople,
      conversationId: message.conversationId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const message = this.createFromForm();
    if (message.id !== undefined) {
      this.subscribeToSaveResponse(this.messageService.update(message));
    } else {
      this.subscribeToSaveResponse(this.messageService.create(message));
    }
  }

  private createFromForm(): IMessage {
    return {
      ...new Message(),
      id: this.editForm.get(['id'])!.value,
      message: this.editForm.get(['message'])!.value,
      sawPeople: this.editForm.get(['sawPeople'])!.value,
      conversationId: this.editForm.get(['conversationId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMessage>>): void {
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

  getSelected(selectedVals: IUser[], option: IUser): IUser {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
