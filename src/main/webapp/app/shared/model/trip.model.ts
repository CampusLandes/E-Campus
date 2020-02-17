import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';

export interface ITrip {
  id?: number;
  creationDate?: Moment;
  departureDate?: Moment;
  startLocalisationName?: string;
  startLocalisationId?: number;
  endLocalisationName?: string;
  endLocalisationId?: number;
  driverLogin?: string;
  driverId?: number;
  passengers?: IUser[];
}

export class Trip implements ITrip {
  constructor(
    public id?: number,
    public creationDate?: Moment,
    public departureDate?: Moment,
    public startLocalisationName?: string,
    public startLocalisationId?: number,
    public endLocalisationName?: string,
    public endLocalisationId?: number,
    public driverLogin?: string,
    public driverId?: number,
    public passengers?: IUser[]
  ) {}
}
