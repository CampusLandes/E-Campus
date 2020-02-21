import { UserType } from 'app/shared/model/enumerations/user-type.model';

export interface ITripEvaluation {
  id?: number;
  note?: number;
  comment?: string;
  type?: UserType;
  evaluatedLogin?: string;
  evaluatedId?: number;
  createdBy?: string;
  createdDate?: Date;
  lastModifiedBy?: string;
  lastModifiedDate?: Date;
  assessorLogin?: string;
  assessorId?: number;
}

export class TripEvaluation implements ITripEvaluation {
  constructor(
    public id?: number,
    public note?: number,
    public comment?: string,
    public type?: UserType,
    public evaluatedLogin?: string,
    public evaluatedId?: number,
    public createdBy?: string,
    public createdDate?: Date,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Date,
    public assessorLogin?: string,
    public assessorId?: number
  ) {}
}
