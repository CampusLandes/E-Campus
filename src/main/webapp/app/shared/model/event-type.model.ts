export interface IEventType {
  id?: number;
  name?: string;
}

export class EventType implements IEventType {
  constructor(public id?: number, public name?: string) {}
}
