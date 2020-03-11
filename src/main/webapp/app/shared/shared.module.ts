import { NgModule } from '@angular/core';
import { ECampusSharedLibsModule } from './shared-libs.module';
import { FindLanguageFromKeyPipe } from './language/find-language-from-key.pipe';
import { AlertComponent } from './alert/alert.component';
import { AlertErrorComponent } from './alert/alert-error.component';
import { LoginModalComponent } from './login/login.component';
import { HasAnyAuthorityDirective } from './auth/has-any-authority.directive';
import { LoginWithoutloginComponent } from './login-withoutlogin/login-withoutlogin.component';

@NgModule({
  imports: [ECampusSharedLibsModule],
  declarations: [
    FindLanguageFromKeyPipe,
    AlertComponent,
    AlertErrorComponent,
    LoginModalComponent,
    HasAnyAuthorityDirective,
    LoginWithoutloginComponent
  ],
  entryComponents: [LoginModalComponent],
  exports: [
    ECampusSharedLibsModule,
    FindLanguageFromKeyPipe,
    AlertComponent,
    AlertErrorComponent,
    LoginModalComponent,
    LoginWithoutloginComponent,
    HasAnyAuthorityDirective
  ]
})
export class ECampusSharedModule {}
