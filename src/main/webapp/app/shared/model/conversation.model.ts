import { IMessage } from 'app/shared/model/message.model';
import { IUser } from 'app/core/user/user.model';
import { ConversationType } from 'app/shared/model/enumerations/conversation-type.model';
import { ConversationPolicyType } from 'app/shared/model/enumerations/conversation-policy-type.model';

export interface IConversation {
  id?: number;
  name?: string;
  type?: ConversationType;
  policyType?: ConversationPolicyType;
  messages?: IMessage[];
  creatorLogin?: string;
  creatorId?: number;
  participants?: IUser[];
}

export class Conversation implements IConversation {
  constructor(
    public id?: number,
    public name?: string,
    public type?: ConversationType,
    public policyType?: ConversationPolicyType,
    public messages?: IMessage[],
    public creatorLogin?: string,
    public creatorId?: number,
    public participants?: IUser[]
  ) {}
}