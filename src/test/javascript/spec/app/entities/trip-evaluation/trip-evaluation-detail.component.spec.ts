import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ECampusTestModule } from '../../../test.module';
import { TripEvaluationDetailComponent } from 'app/entities/trip-evaluation/trip-evaluation-detail.component';
import { TripEvaluation } from 'app/shared/model/trip-evaluation.model';

describe('Component Tests', () => {
  describe('TripEvaluation Management Detail Component', () => {
    let comp: TripEvaluationDetailComponent;
    let fixture: ComponentFixture<TripEvaluationDetailComponent>;
    const route = ({ data: of({ tripEvaluation: new TripEvaluation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ECampusTestModule],
        declarations: [TripEvaluationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TripEvaluationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TripEvaluationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tripEvaluation on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tripEvaluation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
