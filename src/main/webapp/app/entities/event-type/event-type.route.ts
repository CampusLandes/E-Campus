import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEventType, EventType } from 'app/shared/model/event-type.model';
import { EventTypeService } from './event-type.service';
import { EventTypeComponent } from './event-type.component';
import { EventTypeDetailComponent } from './event-type-detail.component';
import { EventTypeUpdateComponent } from './event-type-update.component';

@Injectable({ providedIn: 'root' })
export class EventTypeResolve implements Resolve<IEventType> {
  constructor(private service: EventTypeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEventType> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((eventType: HttpResponse<EventType>) => {
          if (eventType.body) {
            return of(eventType.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EventType());
  }
}

export const eventTypeRoute: Routes = [
  {
    path: '',
    component: EventTypeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'eCampusApp.eventType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EventTypeDetailComponent,
    resolve: {
      eventType: EventTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eCampusApp.eventType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EventTypeUpdateComponent,
    resolve: {
      eventType: EventTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eCampusApp.eventType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EventTypeUpdateComponent,
    resolve: {
      eventType: EventTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eCampusApp.eventType.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
