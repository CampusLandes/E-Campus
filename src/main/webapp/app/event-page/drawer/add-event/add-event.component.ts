import { ILocalisation, Localisation } from './../../../shared/model/localisation.model';
import { Event, IEvent } from 'app/shared/model/event.model';
import { User } from './../../../core/user/user.model';
import { Component, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { IEventType } from 'app/shared/model/event-type.model';
import { SERVER_API_URL } from 'app/app.constants';
import { NzModalRef, NzModalService } from 'ng-zorro-antd';
import { TranslateService } from '@ngx-translate/core';
import { UserService } from 'app/core/user/user.service';
import { EventTypeService } from 'app/entities/event-type/event-type.service';
import { EventService } from 'app/entities/event/event.service';
import { LocalisationService } from 'app/entities/localisation/localisation.service';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { ImportImageComponent } from '../import-image/import-image.component';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import * as moment from 'moment';
import { Observable } from 'rxjs';
import { LocalisationAddComponent } from '../localisation-add/localisation-add.component';
import { TypeAddComponent } from '../type-add/type-add.component';

@Component({
  selector: 'jhi-add-event',
  templateUrl: './add-event.component.html',
  styleUrls: ['./add-event.component.scss']
})
export class AddEventComponent {
  @Input() user: User = new User();

  public resourceUrl = SERVER_API_URL + 'api/events';

  event: Event = new Event();

  validateForm: FormGroup;

  eventtypes: IEventType[] = [];

  localisations: ILocalisation[] = [];

  private localisationTitle = '';
  private eventTypeTitle = '';
  private imageTitle = '';

  isSaving = false;
  isEvent = true;
  isLocalisation = true;

  current = 0;

  localisation: ILocalisation = new Localisation();

  constructor(
    protected eventManager: JhiEventManager,
    private translateService: TranslateService,
    protected eventService: EventService,
    private modalService: NzModalService,
    private modal: NzModalRef,
    protected userService: UserService,
    protected localisationService: LocalisationService,
    protected eventTypeService: EventTypeService,
    private fb: FormBuilder,
    private alertService: JhiAlertService
  ) {
    this.event.responsibleId = this.user.id;
    this.event.responsibleLogin = this.user.login;
    this.validateForm = this.fb.group({
      title: ['', [Validators.required]],
      description: [''],
      date: [''],
      status: ['', [Validators.required]],
      type: ['', [Validators.required]],
      image: [''],
      localisation: ['', [Validators.required]]
    });
    this.translateService.get('eCampusApp.localisation.home.createLabel').subscribe(msg => (this.localisationTitle = msg));
    this.translateService.get('eCampusApp.eventType.home.createLabel').subscribe(msg => (this.eventTypeTitle = msg));
    this.translateService.get('eCampusApp.event.home.imageLabel').subscribe(msg => (this.imageTitle = msg));
    this.localisationService
      .query()
      .pipe(
        map((res: HttpResponse<ILocalisation[]>) => {
          return res.body ? res.body : [];
        })
      )
      .subscribe((resBody: ILocalisation[]) => {
        this.localisations = resBody;
        this.isLocalisation = false;
      });
    this.eventTypeService
      .query()
      .pipe(
        map((res: HttpResponse<IEventType[]>) => {
          return res.body ? res.body : [];
        })
      )
      .subscribe((resBody: IEventType[]) => {
        this.eventtypes = resBody;
        this.isEvent = false;
      });
  }

  public changeImage(): void {
    const modal = this.modalService.create({
      nzTitle: this.imageTitle,
      nzContent: ImportImageComponent,
      nzWidth: '95vw',
      nzComponentParams: {
        responsible: this.user
      },
      nzFooter: null
    });
    // Return a result when closed
    modal.afterClose.subscribe(result => {
      this.validateForm.patchValue({
        image: result.data
      });
    });
  }

  isPrivate(): boolean {
    return this.validateForm.get(['status'])!.value === 'PRIVATE';
  }

  addType(): void {
    const modal = this.modalService.create({
      nzTitle: this.eventTypeTitle,
      nzContent: TypeAddComponent,
      nzFooter: null
    });
    // Return a result when closed
    modal.afterClose.subscribe(result => {
      this.isSaving = true;
      this.isEvent = true;
      this.eventTypeService
        .query()
        .pipe(
          map((res: HttpResponse<IEventType[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IEventType[]) => {
          this.eventtypes = resBody;
          this.isSaving = false;
          this.isEvent = false;
        });
    });
  }

  pre(): void {
    this.current -= 1;
  }

  next(): void {
    if (this.current === 0) {
      this.updateEvent();
      if (this.event.localisationId) {
        const id: number = this.event.localisationId;
        this.localisation = this.localisations.find(i => i.id === this.event.localisationId) as ILocalisation;
      }
    }
    this.current += 1;
  }

  addLocalisation(): void {
    const modal = this.modalService.create({
      nzTitle: this.localisationTitle,
      nzContent: LocalisationAddComponent,
      nzFooter: null
    });
    // Return a result when closed
    modal.afterClose.subscribe(result => {
      this.isSaving = true;
      this.isLocalisation = true;
      this.localisationService
        .query()
        .pipe(
          map((res: HttpResponse<ILocalisation[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: ILocalisation[]) => {
          this.localisations = resBody;
          this.isSaving = false;
          this.isLocalisation = false;
        });
    });
  }

  submitForm(): void {
    this.isSaving = true;
    this.updateEvent();
    // eslint-disable-next-line no-console
    console.log(this.event);
    this.subscribeToSaveResponse(this.eventService.create(this.event));
  }

  updateEvent(): void {
    this.event.title = this.validateForm.get(['title'])!.value;
    this.event.desc = this.validateForm.get(['description'])!.value;
    this.event.completionDate =
      this.validateForm.get(['date'])!.value != null
        ? moment(this.validateForm.get(['date'])!.value, DATE_TIME_FORMAT)
        : this.event.completionDate;
    this.event.status = this.validateForm.get(['status'])!.value;
    this.event.imageUrl = this.validateForm.get(['image'])!.value;
    this.event.typeId = this.validateForm.get(['type'])!.value;
    this.event.typeName = (this.eventtypes.find(i => i.id === this.event.typeId) as IEventType).name;
    this.event.localisationId = this.validateForm.get(['localisation'])!.value;
    this.event.localisationName = (this.localisations.find(i => i.id === this.event.localisationId) as ILocalisation).name;
    this.event.responsibleId = this.user.id;
    this.event.responsibleLogin = this.user.login;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEvent>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      (e: HttpErrorResponse) => this.onSaveError(e)
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.eventManager.broadcast('eventListModification');
    this.modal.destroy();
  }

  protected onSaveError(e: HttpErrorResponse): void {
    this.isSaving = false;
    this.alertService.error('error.http.' + e.status);
  }

  getLocalisationName(): string {
    let text = this.event.localisationName ? this.event.localisationName : '';
    if (this.localisation) {
      text = this.localisation.name + ' - ' + this.localisation.localisation;
    }
    return text;
  }

  resetForm(e: MouseEvent): void {
    e.preventDefault();
    this.validateForm.reset();
  }

  public getUrl(): string {
    return this.validateForm.get(['image'])!.value;
  }

  public getImageUrl(uploaderLogin: String, imageName: String): any {
    return this.resourceUrl + `/image/` + uploaderLogin + `:` + imageName;
  }

  trackById(index: number, item: Event): any {
    return item.id;
  }
}
