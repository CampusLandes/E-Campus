import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITripEvaluation, TripEvaluation } from 'app/shared/model/trip-evaluation.model';
import { TripEvaluationService } from './trip-evaluation.service';
import { TripEvaluationComponent } from './trip-evaluation.component';
import { TripEvaluationDetailComponent } from './trip-evaluation-detail.component';
import { TripEvaluationUpdateComponent } from './trip-evaluation-update.component';

@Injectable({ providedIn: 'root' })
export class TripEvaluationResolve implements Resolve<ITripEvaluation> {
  constructor(private service: TripEvaluationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITripEvaluation> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tripEvaluation: HttpResponse<TripEvaluation>) => {
          if (tripEvaluation.body) {
            return of(tripEvaluation.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TripEvaluation());
  }
}

export const tripEvaluationRoute: Routes = [
  {
    path: '',
    component: TripEvaluationComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'eCampusApp.tripEvaluation.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TripEvaluationDetailComponent,
    resolve: {
      tripEvaluation: TripEvaluationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eCampusApp.tripEvaluation.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TripEvaluationUpdateComponent,
    resolve: {
      tripEvaluation: TripEvaluationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eCampusApp.tripEvaluation.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TripEvaluationUpdateComponent,
    resolve: {
      tripEvaluation: TripEvaluationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eCampusApp.tripEvaluation.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
