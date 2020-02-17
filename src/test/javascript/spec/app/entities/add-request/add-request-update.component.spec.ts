import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ECampusTestModule } from '../../../test.module';
import { AddRequestUpdateComponent } from 'app/entities/add-request/add-request-update.component';
import { AddRequestService } from 'app/entities/add-request/add-request.service';
import { AddRequest } from 'app/shared/model/add-request.model';

describe('Component Tests', () => {
  describe('AddRequest Management Update Component', () => {
    let comp: AddRequestUpdateComponent;
    let fixture: ComponentFixture<AddRequestUpdateComponent>;
    let service: AddRequestService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ECampusTestModule],
        declarations: [AddRequestUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(AddRequestUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AddRequestUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AddRequestService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AddRequest(123);
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
        const entity = new AddRequest();
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
