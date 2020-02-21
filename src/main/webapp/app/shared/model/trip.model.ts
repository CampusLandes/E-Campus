import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';

export interface ITrip {
  id?: number;
  departureDate?: Moment;
  startLocalisationName?: string;
  startLocalisationId?: number;
  endLocalisationName?: string;
  endLocalisationId?: number;
  driverLogin?: string;
  driverId?: number;
  createdBy?: string;
  createdDate?: Date;
  lastModifiedBy?: string;
  lastModifiedDate?: Date;
  passengers?: IUser[];
}

export class Trip implements ITrip {
  constructor(
    public id?: number,
    public departureDate?: Moment,
    public startLocalisationName?: string,
    public startLocalisationId?: number,
    public endLocalisationName?: string,
    public endLocalisationId?: number,
    public driverLogin?: string,
    public driverId?: number,
    public createdBy?: string,
    public createdDate?: Date,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Date,
    public passengers?: IUser[]
  ) {}
}
