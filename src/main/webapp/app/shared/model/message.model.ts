import { IUser } from 'app/core/user/user.model';

export interface IMessage {
  id?: number;
  message?: string;
  sawPeople?: IUser[];
  createdBy?: string;
  createdDate?: Date;
  lastModifiedBy?: string;
  lastModifiedDate?: Date;
  conversationId?: number;
}

export class Message implements IMessage {
  constructor(
    public id?: number,
    public message?: string,
    public sawPeople?: IUser[],
    public createdBy?: string,
    public createdDate?: Date,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Date,
    public conversationId?: number
  ) {}
}
