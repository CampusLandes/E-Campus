import { ECampusSharedModule } from './../shared/shared.module';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { EVENTPAGE_ROUTE } from './event-page.route';
import { EventPageComponent } from './event-page.component';
import { DrawerComponent } from './drawer/drawer.component';

@NgModule({
  imports: [ECampusSharedModule, RouterModule.forChild([EVENTPAGE_ROUTE])],
  declarations: [EventPageComponent, DrawerComponent],
  entryComponents: [DrawerComponent]
})
export class EventPageModule {}
