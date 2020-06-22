import { Component, OnInit, Input } from '@angular/core';
import { SERVER_API_URL } from 'app/app.constants';
import { Event } from 'app/shared/model/event.model';

@Component({
  selector: 'jhi-drawer',
  templateUrl: './drawer.component.html',
  styleUrls: ['./drawer.component.scss']
})
export class DrawerComponent implements OnInit {
  @Input() value: any;
  @Input() type: String = '';
  @Input() user: String = '';

  public event: Event = new Event();

  public resourceUrl = SERVER_API_URL + 'api/events';

  constructor() {}

  ngOnInit(): void {
    this.event = this.value as Event;
  }

  getRow(): number {
    if (this.type === 'edit') {
      return 1;
    } else {
      return 0;
    }
  }

  public getImageUrl(uploaderLogin: String, imageName: String): any {
    return this.resourceUrl + `/image/` + uploaderLogin + `:` + imageName;
  }
}
