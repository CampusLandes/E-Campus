import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'event',
        loadChildren: () => import('./event/event.module').then(m => m.ECampusEventModule)
      },
      {
        path: 'event-type',
        loadChildren: () => import('./event-type/event-type.module').then(m => m.ECampusEventTypeModule)
      },
      {
        path: 'localisation',
        loadChildren: () => import('./localisation/localisation.module').then(m => m.ECampusLocalisationModule)
      },
      {
        path: 'invitation',
        loadChildren: () => import('./invitation/invitation.module').then(m => m.ECampusInvitationModule)
      },
      {
        path: 'add-request',
        loadChildren: () => import('./add-request/add-request.module').then(m => m.ECampusAddRequestModule)
      },
      {
        path: 'trip',
        loadChildren: () => import('./trip/trip.module').then(m => m.ECampusTripModule)
      },
      {
        path: 'trip-evaluation',
        loadChildren: () => import('./trip-evaluation/trip-evaluation.module').then(m => m.ECampusTripEvaluationModule)
      },
      {
        path: 'conversation',
        loadChildren: () => import('./conversation/conversation.module').then(m => m.ECampusConversationModule)
      },
      {
        path: 'message',
        loadChildren: () => import('./message/message.module').then(m => m.ECampusMessageModule)
      },
      {
        path: 'vehicle',
        loadChildren: () => import('./vehicle/vehicle.module').then(m => m.ECampusVehicleModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class ECampusEntityModule {}
