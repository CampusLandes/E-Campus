import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ECampusTestModule } from '../../../test.module';
import { EventTypeUpdateComponent } from 'app/entities/event-type/event-type-update.component';
import { EventTypeService } from 'app/entities/event-type/event-type.service';
import { EventType } from 'app/shared/model/event-type.model';

describe('Component Tests', () => {
  describe('EventType Management Update Component', () => {
    let comp: EventTypeUpdateComponent;
    let fixture: ComponentFixture<EventTypeUpdateComponent>;
    let service: EventTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ECampusTestModule],
        declarations: [EventTypeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EventTypeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EventTypeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EventTypeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EventType(123);
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
        const entity = new EventType();
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
