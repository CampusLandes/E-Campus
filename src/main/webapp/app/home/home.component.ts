import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

import { LoginModalService } from 'app/core/login/login-modal.service';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';
import { IEvent } from 'app/shared/model/event.model';
import { EventService } from 'app/entities/event/event.service';
import { HttpResponse } from '@angular/common/http';
import * as moment from 'moment';
import { SERVER_API_URL } from 'app/app.constants';
import { JhiEventManager } from 'ng-jhipster';

@Component({
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: ['home.scss']
})
export class HomeComponent implements OnInit, OnDestroy {
  public img_url = SERVER_API_URL + 'api/events/image/';
  account: Account | null = null;
  authSubscription?: Subscription;
  publicEventSubscription?: Subscription;
  publicAndPrivateEventSubscription?: Subscription;
  loadingPublic = false;
  publicEvent: IEvent[];
  publicAndPrivateEvent: IEvent[];
  userLoginStatutChangeSubscription?: Subscription;

  constructor(
    private accountService: AccountService,
    private loginModalService: LoginModalService,
    private eventService: EventService,
    private eventManager: JhiEventManager
  ) {
    this.publicEvent = [];
    this.publicAndPrivateEvent = [];
  }

  ngOnInit(): void {
    this.authSubscription = this.accountService.getAuthenticationState().subscribe(account => (this.account = account));
    this.publicEventSubscription = this.eventService.find10RecentPublicEvent().subscribe(
      (res: HttpResponse<IEvent[]>) => (this.publicEvent = res.body ? res.body : []),
      () => {}
    );
    this.userLoginStatutChangeSubscription = this.eventManager.subscribe('UserLoginStatusChange', () => this.loadEventConnected());
    this.loadEventConnected();
  }

  loadEventConnected(): void {
    if (this.accountService.isAuthenticated()) {
      this.publicAndPrivateEventSubscription = this.eventService.find10RecentPublicAndPrivateEvent().subscribe(
        (res: HttpResponse<IEvent[]>) => (this.publicAndPrivateEvent = res.body ? res.body : []),
        () => {}
      );
    }
  }

  isAuthenticated(): boolean {
    return this.accountService.isAuthenticated();
  }

  ngOnDestroy(): void {
    if (this.authSubscription) {
      this.authSubscription.unsubscribe();
    }
    if (this.publicEventSubscription) {
      this.publicEventSubscription.unsubscribe();
    }
    if (this.userLoginStatutChangeSubscription) {
      this.userLoginStatutChangeSubscription.unsubscribe();
    }
    if (this.publicAndPrivateEventSubscription) {
      this.publicAndPrivateEventSubscription.unsubscribe();
    }
  }

  getDate(item: IEvent): string {
    const today = moment();
    let diff = 0;
    if (item.completionDate) {
      diff = item.completionDate.diff(today, 'days');
    }
    return ' ' + diff;
  }

  getimageUrl(event: IEvent): String {
    return this.img_url + event.responsibleLogin + ':' + event.imageUrl;
  }
}
