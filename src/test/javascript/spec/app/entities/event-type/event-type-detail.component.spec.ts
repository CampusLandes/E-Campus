import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ECampusTestModule } from '../../../test.module';
import { EventTypeDetailComponent } from 'app/entities/event-type/event-type-detail.component';
import { EventType } from 'app/shared/model/event-type.model';

describe('Component Tests', () => {
  describe('EventType Management Detail Component', () => {
    let comp: EventTypeDetailComponent;
    let fixture: ComponentFixture<EventTypeDetailComponent>;
    const route = ({ data: of({ eventType: new EventType(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ECampusTestModule],
        declarations: [EventTypeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EventTypeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EventTypeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load eventType on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.eventType).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
