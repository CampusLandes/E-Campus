import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ITripEvaluation, TripEvaluation } from 'app/shared/model/trip-evaluation.model';
import { TripEvaluationService } from './trip-evaluation.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';

@Component({
  selector: 'jhi-trip-evaluation-update',
  templateUrl: './trip-evaluation-update.component.html'
})
export class TripEvaluationUpdateComponent implements OnInit {
  isSaving = false;

  users: IUser[] = [];

  editForm = this.fb.group({
    id: [],
    note: [null, [Validators.required]],
    comment: [],
    type: [null, [Validators.required]],
    evaluatedId: [],
    assessorId: []
  });

  constructor(
    protected tripEvaluationService: TripEvaluationService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tripEvaluation }) => {
      this.updateForm(tripEvaluation);

      this.userService
        .query()
        .pipe(
          map((res: HttpResponse<IUser[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IUser[]) => (this.users = resBody));
    });
  }

  updateForm(tripEvaluation: ITripEvaluation): void {
    this.editForm.patchValue({
      id: tripEvaluation.id,
      note: tripEvaluation.note,
      comment: tripEvaluation.comment,
      type: tripEvaluation.type,
      evaluatedId: tripEvaluation.evaluatedId,
      assessorId: tripEvaluation.assessorId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tripEvaluation = this.createFromForm();
    if (tripEvaluation.id !== undefined) {
      this.subscribeToSaveResponse(this.tripEvaluationService.update(tripEvaluation));
    } else {
      this.subscribeToSaveResponse(this.tripEvaluationService.create(tripEvaluation));
    }
  }

  private createFromForm(): ITripEvaluation {
    return {
      ...new TripEvaluation(),
      id: this.editForm.get(['id'])!.value,
      note: this.editForm.get(['note'])!.value,
      comment: this.editForm.get(['comment'])!.value,
      type: this.editForm.get(['type'])!.value,
      evaluatedId: this.editForm.get(['evaluatedId'])!.value,
      assessorId: this.editForm.get(['assessorId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITripEvaluation>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IUser): any {
    return item.id;
  }
}
