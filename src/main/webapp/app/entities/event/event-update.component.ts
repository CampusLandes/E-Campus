import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpClient, HttpRequest, HttpEvent, HttpEventType, HttpHeaders } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable, forkJoin } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IEvent, Event } from 'app/shared/model/event.model';
import { EventService } from './event.service';
import { IEventType } from 'app/shared/model/event-type.model';
import { EventTypeService } from 'app/entities/event-type/event-type.service';
import { ILocalisation } from 'app/shared/model/localisation.model';
import { LocalisationService } from 'app/entities/localisation/localisation.service';
import { IUser, User } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { SERVER_API_URL } from 'app/app.constants';
import { AuthServerProvider } from 'app/core/auth/auth-jwt.service';
import { FileUploader } from 'ng2-file-upload';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';

type SelectableEntity = IEventType | ILocalisation | IUser;

@Component({
  selector: 'jhi-event-update',
  templateUrl: './event-update.component.html'
})
export class EventUpdateComponent implements OnInit {
  public resourceUrl = SERVER_API_URL + 'api/events/upload/';
  public img_url = SERVER_API_URL + 'api/events/image/';
  isSaving = false;

  public img = '';
  public haveimg = true;

  eventtypes: IEventType[] = [];

  localisations: ILocalisation[] = [];

  users: IUser[] = [];

  currentUser: IUser = new User();
  account!: Account;

  public uploader: FileUploader;

  editForm = this.fb.group({
    id: [],
    title: [null, [Validators.required]],
    desc: [],
    completionDate: [],
    status: [null, [Validators.required]],
    imageUrl: [],
    typeId: [null, Validators.required],
    localisationId: [null, Validators.required],
    responsibleId: [null, Validators.required],
    participants: []
  });

  constructor(
    protected eventService: EventService,
    protected eventTypeService: EventTypeService,
    protected localisationService: LocalisationService,
    protected userService: UserService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private authServerProvider: AuthServerProvider,
    private http: HttpClient
  ) {
    this.uploader = new FileUploader({
      url: this.resourceUrl,
      isHTML5: true,
      allowedFileType: ['image'],
      maxFileSize: 5 * 1024 * 1024,
      authTokenHeader: 'Authorization',
      authToken: 'Bearer ' + this.authServerProvider.getToken()
    });
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ event }) => {
      this.updateForm(event);

      this.accountService.identity(true).subscribe(account => {
        if (account) {
          this.account = account;
          this.userService.find(this.account.login).subscribe(resBody => {
            if (resBody) {
              this.currentUser = resBody;
              if (!event.responsibleId) this.updateResponsible(this.currentUser.id, event);
            }
          });
        }
      });
      this.eventTypeService
        .query()
        .pipe(
          map((res: HttpResponse<IEventType[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IEventType[]) => (this.eventtypes = resBody));

      this.localisationService
        .query()
        .pipe(
          map((res: HttpResponse<ILocalisation[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: ILocalisation[]) => (this.localisations = resBody));

      this.userService
        .query()
        .pipe(
          map((res: HttpResponse<IUser[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IUser[]) => {
          this.users = resBody;
        });
    });
    this.uploader.onCompleteItem = (item: any, response: any, status: any, headers: any) => {
      this.editForm.get(['imageUrl'])!.setValue(response);
      this.img = response;
      this.haveimg = true;
    };
  }

  updateForm(event: IEvent): void {
    this.editForm.patchValue({
      id: event.id,
      title: event.title,
      desc: event.desc,
      completionDate: event.completionDate != null ? event.completionDate.format(DATE_TIME_FORMAT) : null,
      status: event.status,
      imageUrl: event.imageUrl,
      typeId: event.typeId,
      localisationId: event.localisationId,
      responsibleId: event.responsibleId,
      participants: event.participants
    });
  }

  getimageUrl(): String {
    return this.img_url + this.account.login + ':' + this.img;
  }

  updateResponsible(id: number, event: IEvent): void {
    this.editForm.patchValue({
      id: event.id,
      title: event.title,
      desc: event.desc,
      completionDate: event.completionDate != null ? event.completionDate.format(DATE_TIME_FORMAT) : null,
      status: event.status,
      imageUrl: event.imageUrl,
      typeId: event.typeId,
      localisationId: event.localisationId,
      responsibleId: id,
      participants: event.participants
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const event = this.createFromForm();
    if (event.id !== undefined) {
      this.subscribeToSaveResponse(this.eventService.update(event));
    } else {
      this.subscribeToSaveResponse(this.eventService.create(event));
    }
  }

  private createFromForm(): IEvent {
    return {
      ...new Event(),
      id: this.editForm.get(['id'])!.value,
      title: this.editForm.get(['title'])!.value,
      desc: this.editForm.get(['desc'])!.value,
      completionDate:
        this.editForm.get(['completionDate'])!.value != null
          ? moment(this.editForm.get(['completionDate'])!.value, DATE_TIME_FORMAT)
          : undefined,
      status: this.editForm.get(['status'])!.value,
      imageUrl: this.editForm.get(['imageUrl'])!.value,
      typeId: this.editForm.get(['typeId'])!.value,
      localisationId: this.editForm.get(['localisationId'])!.value,
      responsibleId: this.editForm.get(['responsibleId'])!.value,
      participants: this.editForm.get(['participants'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEvent>>): void {
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
