import { HttpResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { LocalisationService } from 'app/entities/localisation/localisation.service';
import { ILocalisation, Localisation } from 'app/shared/model/localisation.model';
import { NzModalRef } from 'ng-zorro-antd';
import { Observable } from 'rxjs';

@Component({
  selector: 'jhi-localisation-add',
  templateUrl: './localisation-add.component.html',
  styleUrls: ['./localisation-add.component.scss']
})
export class LocalisationAddComponent {
  isSaving: Boolean = false;
  validateForm: FormGroup;

  constructor(private fb: FormBuilder, protected localisationService: LocalisationService, private modal: NzModalRef) {
    this.validateForm = this.fb.group({
      name: ['', [Validators.required]],
      localisation: [null, [Validators.required]],
      gpsPosition: []
    });
  }

  submitForm(): void {
    this.isSaving = true;
    const eventType = this.createFromForm();
    if (eventType.id !== undefined) {
      this.subscribeToSaveResponse(this.localisationService.update(eventType));
    } else {
      this.subscribeToSaveResponse(this.localisationService.create(eventType));
    }
  }

  private createFromForm(): ILocalisation {
    return {
      ...new Localisation(),
      name: this.validateForm.get(['name'])!.value,
      localisation: this.validateForm.get(['localisation'])!.value,
      gpsPosition: this.validateForm.get(['gpsPosition'])!.value
    };
  }

  resetForm(e: MouseEvent): void {
    e.preventDefault();
    this.validateForm.reset();
    this.modal.destroy();
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILocalisation>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.modal.destroy();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
