import { InvitationStatus } from 'app/shared/model/enumerations/invitation-status.model';

export interface IAddRequest {
  id?: number;
  message?: string;
  status?: InvitationStatus;
  eventTitle?: string;
  eventId?: number;
  createdBy?: string;
  createdDate?: Date;
  lastModifiedBy?: string;
  lastModifiedDate?: Date;
  validatorLogin?: string;
  validatorId?: number;
}

export class AddRequest implements IAddRequest {
  constructor(
    public id?: number,
    public message?: string,
    public status?: InvitationStatus,
    public eventTitle?: string,
    public eventId?: number,
    public createdBy?: string,
    public createdDate?: Date,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Date,
    public validatorLogin?: string,
    public validatorId?: number
  ) {}
}
