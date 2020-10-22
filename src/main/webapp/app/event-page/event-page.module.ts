import { ECampusSharedModule } from './../shared/shared.module';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { EVENTPAGE_ROUTE } from './event-page.route';
import { EventPageComponent } from './event-page.component';
import { DrawerComponent } from './drawer/drawer.component';
import { ImportImageComponent } from './drawer/import-image/import-image.component';
import { FileUploadModule } from 'ng2-file-upload';
import { TypeAddComponent } from './drawer/type-add/type-add.component';
import { LocalisationAddComponent } from './drawer/localisation-add/localisation-add.component';
import { ImageViewerComponent } from './drawer/image-viewer/image-viewer.component';
import { AddEventComponent } from './drawer/add-event/add-event.component';

@NgModule({
  imports: [ECampusSharedModule, RouterModule.forChild([EVENTPAGE_ROUTE]), FileUploadModule],
  declarations: [
    EventPageComponent,
    DrawerComponent,
    ImportImageComponent,
    TypeAddComponent,
    LocalisationAddComponent,
    ImageViewerComponent,
    AddEventComponent
  ],
  entryComponents: [
    DrawerComponent,
    ImportImageComponent,
    TypeAddComponent,
    LocalisationAddComponent,
    ImageViewerComponent,
    AddEventComponent
  ]
})
export class EventPageModule {}
