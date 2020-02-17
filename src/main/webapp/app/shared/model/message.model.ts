import { IUser } from 'app/core/user/user.model';

export interface IMessage {
  id?: number;
  message?: string;
  sawPeople?: IUser[];
  conversationId?: number;
}

export class Message implements IMessage {
  constructor(public id?: number, public message?: string, public sawPeople?: IUser[], public conversationId?: number) {}
}
