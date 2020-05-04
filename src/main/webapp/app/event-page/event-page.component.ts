import { EventService } from 'app/entities/event/event.service';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { IEvent, Event } from './../shared/model/event.model';
import { Component, OnInit } from '@angular/core';
import { BehaviorSubject, Subscription, Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { CollectionViewer, DataSource } from '@angular/cdk/collections';

class MyDataSource extends DataSource<Event> {
  private length = 10;
  private pageSize = 10;
  private cachedData = Array.from<Event>({ length: this.length });
  private fetchedPages = new Set<number>();
  private dataStream = new BehaviorSubject<Event[]>(this.cachedData);
  private subscription = new Subscription();

  public resourceUrl = SERVER_API_URL + 'api/events';

  constructor(private http: HttpClient, private eventService: EventService) {
    super();
  }

  connect(collectionViewer: CollectionViewer): Observable<IEvent[]> {
    this.subscription.add(
      collectionViewer.viewChange.subscribe(range => {
        const startPage = this.getPageForIndex(range.start);
        const endPage = this.getPageForIndex(range.end - 1);
        for (let i = startPage; i <= endPage; i++) {
          this.fetchPage(i);
        }
      })
    );
    return this.dataStream;
  }

  disconnect(): void {
    this.subscription.unsubscribe();
  }

  private getPageForIndex(index: number): number {
    return Math.floor(index / this.pageSize);
  }

  private fetchPage(pageNumber: number): void {
    if (this.fetchedPages.has(pageNumber)) {
      return;
    }
    this.fetchedPages.add(pageNumber);

    this.eventService
      .query({
        page: pageNumber,
        size: this.pageSize,
        sort: ['completionDate,desc']
      })
      .subscribe(
        (res: HttpResponse<Event[]>) => this.onSuccess(res, res.body, res.headers, pageNumber),
        () => this.onError()
      );
  }

  // sort(): string[] {
  //   return ["completionDate,desc"];
  // }

  protected onSuccess(res: any, data: Event[] | null, headers: HttpHeaders, page: number): void {
    this.cachedData.splice(page * this.pageSize, this.pageSize, res);
    this.dataStream.next(this.cachedData);
  }

  protected onError(): void {}
}

// this.http.get<Event[]>(this.resourceUrl + `/?size=${this.pageSize}&&page=${page}&&sort=completionDate,desc`).subscribe(res => {
//   this.cachedData.splice(page * this.pageSize, this.pageSize, ...res);
//   this.dataStream.next(this.cachedData);
// });

// ------------------------------------------------------------------------------------------------------------------------------

@Component({
  selector: 'jhi-event-page',
  templateUrl: './event-page.component.html',
  styleUrls: ['./event-page.component.scss']
})
export class EventPageComponent implements OnInit {
  ds = new MyDataSource(this.http, this.eventService);

  public resourceUrl = SERVER_API_URL + 'api/events';

  constructor(private http: HttpClient, private eventService: EventService) {}

  ngOnInit(): void {}

  private getImageUrl(uploaderLogin: String, imageName: String): any {
    return this.resourceUrl + `/image/` + uploaderLogin + `:` + imageName;
  }
}
