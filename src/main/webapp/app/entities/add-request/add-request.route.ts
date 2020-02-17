import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAddRequest, AddRequest } from 'app/shared/model/add-request.model';
import { AddRequestService } from './add-request.service';
import { AddRequestComponent } from './add-request.component';
import { AddRequestDetailComponent } from './add-request-detail.component';
import { AddRequestUpdateComponent } from './add-request-update.component';

@Injectable({ providedIn: 'root' })
export class AddRequestResolve implements Resolve<IAddRequest> {
  constructor(private service: AddRequestService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAddRequest> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((addRequest: HttpResponse<AddRequest>) => {
          if (addRequest.body) {
            return of(addRequest.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new AddRequest());
  }
}

export const addRequestRoute: Routes = [
  {
    path: '',
    component: AddRequestComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'eCampusApp.addRequest.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AddRequestDetailComponent,
    resolve: {
      addRequest: AddRequestResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eCampusApp.addRequest.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AddRequestUpdateComponent,
    resolve: {
      addRequest: AddRequestResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eCampusApp.addRequest.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AddRequestUpdateComponent,
    resolve: {
      addRequest: AddRequestResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eCampusApp.addRequest.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
