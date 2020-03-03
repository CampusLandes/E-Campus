import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgJhipsterModule } from 'ng-jhipster';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { TranslateModule } from '@ngx-translate/core';
import { NgZorroAntdModule, NZ_I18N, en_US, NZ_ICONS } from 'ng-zorro-antd';
import { HttpClientModule } from '@angular/common/http';

/** config angular i18n **/
import { registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';
registerLocaleData(en);

import * as AllIcons from '@ant-design/icons-angular/icons';
import { IconDefinition } from '@fortawesome/free-solid-svg-icons';
import { ScrollingModule } from '@angular/cdk/scrolling';

const antDesignIcons = (AllIcons as unknown) as {
  [key: string]: IconDefinition;
};
const icons: IconDefinition[] = Object.keys(antDesignIcons).map(key => antDesignIcons[key]);

@NgModule({
  imports: [NgZorroAntdModule, ScrollingModule, FormsModule, HttpClientModule],

  exports: [
    NgZorroAntdModule,
    ScrollingModule,
    FormsModule,
    HttpClientModule,
    CommonModule,
    NgbModule,
    NgJhipsterModule,
    InfiniteScrollModule,
    FontAwesomeModule,
    ReactiveFormsModule,
    TranslateModule
  ],
  providers: [
    { provide: NZ_I18N, useValue: en_US },
    { provide: NZ_ICONS, useValue: icons }
  ]
})
export class ECampusSharedLibsModule {}
