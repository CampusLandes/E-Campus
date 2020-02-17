import { UserType } from 'app/shared/model/enumerations/user-type.model';

export interface ITripEvaluation {
  id?: number;
  note?: number;
  comment?: string;
  type?: UserType;
  evaluatedLogin?: string;
  evaluatedId?: number;
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
    public assessorLogin?: string,
    public assessorId?: number
  ) {}
}
