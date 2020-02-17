import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ECampusTestModule } from '../../../test.module';
import { TripEvaluationUpdateComponent } from 'app/entities/trip-evaluation/trip-evaluation-update.component';
import { TripEvaluationService } from 'app/entities/trip-evaluation/trip-evaluation.service';
import { TripEvaluation } from 'app/shared/model/trip-evaluation.model';

describe('Component Tests', () => {
  describe('TripEvaluation Management Update Component', () => {
    let comp: TripEvaluationUpdateComponent;
    let fixture: ComponentFixture<TripEvaluationUpdateComponent>;
    let service: TripEvaluationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ECampusTestModule],
        declarations: [TripEvaluationUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(TripEvaluationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TripEvaluationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TripEvaluationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TripEvaluation(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new TripEvaluation();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
