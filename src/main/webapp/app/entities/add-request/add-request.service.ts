import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAddRequest } from 'app/shared/model/add-request.model';

type EntityResponseType = HttpResponse<IAddRequest>;
type EntityArrayResponseType = HttpResponse<IAddRequest[]>;

@Injectable({ providedIn: 'root' })
export class AddRequestService {
  public resourceUrl = SERVER_API_URL + 'api/add-requests';

  constructor(protected http: HttpClient) {}

  create(addRequest: IAddRequest): Observable<EntityResponseType> {
    return this.http.post<IAddRequest>(this.resourceUrl, addRequest, { observe: 'response' });
  }

  update(addRequest: IAddRequest): Observable<EntityResponseType> {
    return this.http.put<IAddRequest>(this.resourceUrl, addRequest, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAddRequest>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAddRequest[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
