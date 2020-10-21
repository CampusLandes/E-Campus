import { Component, Input } from '@angular/core';
import { SERVER_API_URL } from 'app/app.constants';

@Component({
  selector: 'jhi-image-viewer',
  templateUrl: './image-viewer.component.html',
  styleUrls: ['./image-viewer.component.scss']
})
export class ImageViewerComponent {
  @Input() resp: String = '';
  @Input() url: String = '';

  public resourceUrl = SERVER_API_URL + 'api/events';

  constructor() {}

  public getImageUrl(uploaderLogin: String, imageName: String): any {
    return this.resourceUrl + `/image/` + uploaderLogin + `:` + imageName;
  }
}
