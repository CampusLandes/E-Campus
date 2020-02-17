import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITripEvaluation } from 'app/shared/model/trip-evaluation.model';

@Component({
  selector: 'jhi-trip-evaluation-detail',
  templateUrl: './trip-evaluation-detail.component.html'
})
export class TripEvaluationDetailComponent implements OnInit {
  tripEvaluation: ITripEvaluation | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tripEvaluation }) => {
      this.tripEvaluation = tripEvaluation;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
