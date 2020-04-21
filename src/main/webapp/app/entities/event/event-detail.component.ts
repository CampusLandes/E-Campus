import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEvent } from 'app/shared/model/event.model';
import { SERVER_API_URL } from 'app/app.constants';

@Component({
  selector: 'jhi-event-detail',
  templateUrl: './event-detail.component.html'
})
export class EventDetailComponent implements OnInit {
  event: IEvent | null = null;

  public img_url = SERVER_API_URL + 'api/events/image/';

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ event }) => {
      this.event = event;
    });
  }

  getimageUrl(event: IEvent): String {
    return this.img_url + event.responsibleLogin + ':' + event.imageUrl;
  }

  previousState(): void {
    window.history.back();
  }
}
