export interface ILocalisation {
  id?: number;
  name?: string;
  localisation?: string;
  gpsPosition?: string;
}

export class Localisation implements ILocalisation {
  constructor(public id?: number, public name?: string, public localisation?: string, public gpsPosition?: string) {}
}
