import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';
import { EventStatus } from 'app/shared/model/enumerations/event-status.model';

export interface IEvent {
  id?: number;
  title?: string;
  desc?: string;
  completionDate?: Moment;
  status?: EventStatus;
  typeName?: string;
  typeId?: number;
  localisationName?: string;
  localisationId?: number;
  responsibleLogin?: string;
  responsibleId?: number;
  participants?: IUser[];
}

export class Event implements IEvent {
  constructor(
    public id?: number,
    public title?: string,
    public desc?: string,
    public completionDate?: Moment,
    public status?: EventStatus,
    public typeName?: string,
    public typeId?: number,
    public localisationName?: string,
    public localisationId?: number,
    public responsibleLogin?: string,
    public responsibleId?: number,
    public participants?: IUser[]
  ) {}
}
