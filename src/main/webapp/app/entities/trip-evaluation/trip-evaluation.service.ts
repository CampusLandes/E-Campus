import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITripEvaluation } from 'app/shared/model/trip-evaluation.model';

type EntityResponseType = HttpResponse<ITripEvaluation>;
type EntityArrayResponseType = HttpResponse<ITripEvaluation[]>;

@Injectable({ providedIn: 'root' })
export class TripEvaluationService {
  public resourceUrl = SERVER_API_URL + 'api/trip-evaluations';

  constructor(protected http: HttpClient) {}

  create(tripEvaluation: ITripEvaluation): Observable<EntityResponseType> {
    return this.http.post<ITripEvaluation>(this.resourceUrl, tripEvaluation, { observe: 'response' });
  }

  update(tripEvaluation: ITripEvaluation): Observable<EntityResponseType> {
    return this.http.put<ITripEvaluation>(this.resourceUrl, tripEvaluation, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITripEvaluation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITripEvaluation[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
