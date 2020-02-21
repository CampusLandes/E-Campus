import { InvitationStatus } from 'app/shared/model/enumerations/invitation-status.model';

export interface IInvitation {
  id?: number;
  message?: string;
  status?: InvitationStatus;
  eventTitle?: string;
  eventId?: number;
  createdBy?: string;
  createdDate?: Date;
  lastModifiedBy?: string;
  lastModifiedDate?: Date;
  userLogin?: string;
  userId?: number;
}

export class Invitation implements IInvitation {
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
    public userLogin?: string,
    public userId?: number
  ) {}
}
