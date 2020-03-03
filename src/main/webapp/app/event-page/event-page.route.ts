import { EventPageComponent } from './event-page.component';
import { Route } from '@angular/router';

export const EVENTPAGE_ROUTE: Route = {
  path: 'event-page',
  component: EventPageComponent,
  data: {
    authorities: [],
    pageTitle: 'eCampusApp.eventPage.title'
  }
};
