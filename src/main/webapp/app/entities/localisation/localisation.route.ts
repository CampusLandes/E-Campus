import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ILocalisation, Localisation } from 'app/shared/model/localisation.model';
import { LocalisationService } from './localisation.service';
import { LocalisationComponent } from './localisation.component';
import { LocalisationDetailComponent } from './localisation-detail.component';
import { LocalisationUpdateComponent } from './localisation-update.component';

@Injectable({ providedIn: 'root' })
export class LocalisationResolve implements Resolve<ILocalisation> {
  constructor(private service: LocalisationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILocalisation> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((localisation: HttpResponse<Localisation>) => {
          if (localisation.body) {
            return of(localisation.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Localisation());
  }
}

export const localisationRoute: Routes = [
  {
    path: '',
    component: LocalisationComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'eCampusApp.localisation.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: LocalisationDetailComponent,
    resolve: {
      localisation: LocalisationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eCampusApp.localisation.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: LocalisationUpdateComponent,
    resolve: {
      localisation: LocalisationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eCampusApp.localisation.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: LocalisationUpdateComponent,
    resolve: {
      localisation: LocalisationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eCampusApp.localisation.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
