import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ECampusTestModule } from '../../../test.module';
import { AddRequestDetailComponent } from 'app/entities/add-request/add-request-detail.component';
import { AddRequest } from 'app/shared/model/add-request.model';

describe('Component Tests', () => {
  describe('AddRequest Management Detail Component', () => {
    let comp: AddRequestDetailComponent;
    let fixture: ComponentFixture<AddRequestDetailComponent>;
    const route = ({ data: of({ addRequest: new AddRequest(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ECampusTestModule],
        declarations: [AddRequestDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(AddRequestDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AddRequestDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load addRequest on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.addRequest).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
