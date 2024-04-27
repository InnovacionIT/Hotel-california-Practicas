export class Habitacion {
  constructor(
    public habitacionId:number,
    public numero:number,
    public estado:string,
    public precio:number,
    public hotelId:number,
    public imagenes:Array<any>,
    public piso:number,
    public tipoHabitacion:string,
    //public descripcion:string,
    public servicios:Array<any>,
    //public disponible:boolean,

    ){}
}
