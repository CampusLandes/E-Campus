import { InvitationStatus } from 'app/shared/model/enumerations/invitation-status.model';

export interface IAddRequest {
  id?: number;
  message?: string;
  status?: InvitationStatus;
  eventTitle?: string;
  eventId?: number;
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
    public validatorLogin?: string,
    public validatorId?: number
  ) {}
}
