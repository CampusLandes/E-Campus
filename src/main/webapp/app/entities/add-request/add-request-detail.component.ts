import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAddRequest } from 'app/shared/model/add-request.model';

@Component({
  selector: 'jhi-add-request-detail',
  templateUrl: './add-request-detail.component.html'
})
export class AddRequestDetailComponent implements OnInit {
  addRequest: IAddRequest | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ addRequest }) => {
      this.addRequest = addRequest;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
