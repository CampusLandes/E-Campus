import { Localisation } from './../../shared/model/localisation.model';
import { LocalisationAddComponent } from './localisation-add/localisation-add.component';
import { TypeAddComponent } from './type-add/type-add.component';
import { ImportImageComponent } from './import-image/import-image.component';
import { User } from 'app/core/user/user.model';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SERVER_API_URL } from 'app/app.constants';
import { UserService } from 'app/core/user/user.service';
import { EventTypeService } from 'app/entities/event-type/event-type.service';
import { EventType, IEventType } from 'app/shared/model/event-type.model';
import { Event, IEvent } from 'app/shared/model/event.model';
import { map } from 'rxjs/operators';
import { NzDrawerRef, NzModalService } from 'ng-zorro-antd';
import { EventService } from 'app/entities/event/event.service';
import { Observable } from 'rxjs';
import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import * as moment from 'moment';
import { LocalisationService } from 'app/entities/localisation/localisation.service';
import { ILocalisation } from 'app/shared/model/localisation.model';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'jhi-drawer',
  templateUrl: './drawer.component.html',
  styleUrls: ['./drawer.component.scss']
})
export class DrawerComponent implements OnInit {
  @Input() value: any;
  @Input() type: String = '';
  @Input() isResp: Boolean = false;

  validateForm: FormGroup;

  isSaving = false;

  responsible: User;

  eventtypes: IEventType[] = [];

  localisations: ILocalisation[] = [];
  localisation: ILocalisation = new Localisation();
  isEvent = true;
  isLocalisation = true;

  private localisationTitle = '';
  private eventTypeTitle = '';
  private imageTitle = '';

  public event: Event = new Event();

  public resourceUrl = SERVER_API_URL + 'api/events';

  constructor(
    protected eventManager: JhiEventManager,
    private translateService: TranslateService,
    protected eventService: EventService,
    private modalService: NzModalService,
    protected userService: UserService,
    protected localisationService: LocalisationService,
    protected eventTypeService: EventTypeService,
    private drawerRef: NzDrawerRef<string>,
    private fb: FormBuilder,
    private alertService: JhiAlertService
  ) {
    this.validateForm = this.fb.group({
      title: ['', [Validators.required]],
      description: [''],
      date: [''],
      status: ['', [Validators.required]],
      type: ['', [Validators.required]],
      image: [''],
      localisation: ['', [Validators.required]]
    });
    this.responsible = new User();
  }

  ngOnInit(): void {
    this.event = this.value as Event;
    let dates = '';
    if (this.event.completionDate) {
      dates = moment(this.event.completionDate, DATE_FORMAT).toString();
    }
    this.validateForm.patchValue({
      title: this.event.title,
      description: this.event.desc,
      date: dates,
      status: this.event.status,
      type: this.event.typeId,
      image: this.event.imageUrl,
      localisation: this.event.localisationId
    });
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
        if (this.event.localisationId) {
          const id: number = this.event.localisationId;
          this.localisation = this.localisations.find(i => i.id === this.event.localisationId) as ILocalisation;
        }
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
    this.userService.find(this.event.responsibleLogin ? this.event.responsibleLogin : '').subscribe(resBody => {
      if (resBody) {
        this.responsible = resBody;
      }
    });
    this.translateService.get('eCampusApp.localisation.home.createLabel').subscribe(msg => (this.localisationTitle = msg));
    this.translateService.get('eCampusApp.eventType.home.createLabel').subscribe(msg => (this.eventTypeTitle = msg));
    this.translateService.get('eCampusApp.event.home.imageLabel').subscribe(msg => (this.imageTitle = msg));
  }

  public changeImage(): void {
    const modal = this.modalService.create({
      nzTitle: this.imageTitle,
      nzContent: ImportImageComponent,
      nzWidth: '95vw',
      nzComponentParams: {
        responsible: this.responsible
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

  getRow(): number {
    if (this.type === 'edit') {
      return 1;
    } else {
      return 0;
    }
  }

  getLocalisationName(): string {
    let text = this.event.localisationName ? this.event.localisationName : '';
    if (this.localisation) {
      text = this.localisation.name + ' - ' + this.localisation.localisation;
    }
    return text;
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
          this.isLocalisation = true;
        });
    });
  }

  submitForm(): void {
    this.isSaving = true;
    const date = this.event.completionDate;
    this.updateEvent();
    if (!moment(this.validateForm.get(['date'])!.value, DATE_TIME_FORMAT).isValid()) {
      this.event.completionDate = date;
    }
    this.subscribeToSaveResponse(this.eventService.update(this.event));
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
    this.drawerRef.close();
  }

  protected onSaveError(e: HttpErrorResponse): void {
    this.isSaving = false;
    this.alertService.error('error.http.' + e.status);
  }

  resetForm(e: MouseEvent): void {
    e.preventDefault();
    let dates = '';
    if (this.event.completionDate) {
      dates = moment(this.event.completionDate, DATE_FORMAT).toString();
    }
    this.validateForm.patchValue({
      title: this.event.title,
      description: this.event.desc,
      date: dates,
      status: this.event.status,
      type: this.event.typeId,
      image: this.event.imageUrl,
      localisation: this.event.localisationId
    });
  }

  public getUrl(): string {
    return this.validateForm.get(['image'])!.value;
  }

  public getImageUrl(uploaderLogin: String, imageName: String): any {
    return this.resourceUrl + `/image/` + uploaderLogin + `:` + imageName;
  }

  trackById(index: number, item: EventType): any {
    return item.id;
  }
}
