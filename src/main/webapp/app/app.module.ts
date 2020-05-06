import { EventPageModule } from './event-page/event-page.module';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { ECampusSharedModule } from 'app/shared/shared.module';
import { ECampusCoreModule } from 'app/core/core.module';
import { ECampusAppRoutingModule } from './app-routing.module';
import { ECampusHomeModule } from './home/home.module';
import { ECampusEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    ECampusSharedModule,
    ECampusCoreModule,
    ECampusHomeModule,
    EventPageModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    ECampusEntityModule,
    ECampusAppRoutingModule
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent]
})
export class ECampusAppModule {}
