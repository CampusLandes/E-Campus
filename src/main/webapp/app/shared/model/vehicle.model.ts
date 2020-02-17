export interface IVehicle {
  id?: number;
  type?: string;
  nbPlace?: number;
  bagage?: string;
  trunkVolume?: number;
  smoking?: boolean;
  userLogin?: string;
  userId?: number;
}

export class Vehicle implements IVehicle {
  constructor(
    public id?: number,
    public type?: string,
    public nbPlace?: number,
    public bagage?: string,
    public trunkVolume?: number,
    public smoking?: boolean,
    public userLogin?: string,
    public userId?: number
  ) {
    this.smoking = this.smoking || false;
  }
}
