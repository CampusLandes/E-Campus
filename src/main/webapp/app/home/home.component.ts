import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

import { LoginModalService } from 'app/core/login/login-modal.service';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';
import { IEvent } from 'app/shared/model/event.model';
import { EventService } from 'app/entities/event/event.service';
import { HttpResponse } from '@angular/common/http';
import * as moment from 'moment';

@Component({
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: ['home.scss']
})
export class HomeComponent implements OnInit, OnDestroy {
  account: Account | null = null;
  authSubscription?: Subscription;
  publicEventSubscription?: Subscription;
  loadingPublic = false;
  publicEvent: IEvent[];

  constructor(private accountService: AccountService, private loginModalService: LoginModalService, private eventService: EventService) {
    this.publicEvent = [];
  }

  ngOnInit(): void {
    this.authSubscription = this.accountService.getAuthenticationState().subscribe(account => (this.account = account));
    this.publicEventSubscription = this.eventService.find10RecentPublicEvent().subscribe(
      (res: HttpResponse<IEvent[]>) => (this.publicEvent = res.body ? res.body : []),
      () => {}
    );
  }

  isAuthenticated(): boolean {
    return this.accountService.isAuthenticated();
  }

  login(): void {
    this.loginModalService.open();
  }

  ngOnDestroy(): void {
    if (this.authSubscription) {
      this.authSubscription.unsubscribe();
    }
    if (this.publicEventSubscription) {
      this.publicEventSubscription.unsubscribe();
    }
  }

  getDate(item: IEvent): string {
    const today = moment();
    let diff = 0;
    if (item.completionDate) {
      diff = item.completionDate.diff(today, 'days');
      //if (diff <0){
      //  Code de suppression de l'event
      //}
    }
    return ' ' + diff;
  }
}
