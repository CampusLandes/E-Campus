import { Component, Input } from '@angular/core';
import { NzModalRef } from 'ng-zorro-antd';
import { FileUploader } from 'ng2-file-upload';
import { SERVER_API_URL } from 'app/app.constants';
import { AuthServerProvider } from 'app/core/auth/auth-jwt.service';
import { JhiAlertService } from 'ng-jhipster';

@Component({
  selector: 'jhi-import-image',
  templateUrl: './import-image.component.html',
  styleUrls: ['./import-image.component.scss']
})
export class ImportImageComponent {
  public resourceUrl = SERVER_API_URL + 'api/events/upload/';
  @Input() responsible: any;

  public uploader: FileUploader;
  private url: String = '';

  constructor(private alertService: JhiAlertService, private authServerProvider: AuthServerProvider, private modal: NzModalRef) {
    this.uploader = new FileUploader({
      url: this.resourceUrl,
      isHTML5: true,
      allowedFileType: ['image'],
      maxFileSize: 1 * 1024 * 1024,
      authTokenHeader: 'Authorization',
      authToken: 'Bearer ' + this.authServerProvider.getToken()
    });
    this.uploader.onCompleteItem = (item: any, response: any, status: any) => {
      if (status === 200) {
        this.url = response;
        this.destroyModal();
      } else {
        this.alertService.error('error.http.' + status);
      }
    };
  }

  destroyModal(): void {
    this.modal.destroy({ data: this.url });
  }
}
