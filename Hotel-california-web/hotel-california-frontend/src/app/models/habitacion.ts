export class Habitacion {
  constructor(
    public id: number,
    public habitacionId:number,
    public numero:number,
    public estado:string,
    public precio:number,
    public hotelId:number,
    //public imagenes:Array<any>,
    public imagenes:string,
    public piso:number,
    public tipoHabitacion:string,
    //public descripcion:string,
    public servicios:string[],
    //public disponible:boolean,

    ){}
}
