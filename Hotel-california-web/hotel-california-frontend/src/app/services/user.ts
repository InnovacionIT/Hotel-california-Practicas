export interface User {
    usuarioId:number;
    nombre?:string;
    apellido?:string;
    usuario: string;
    message?:string;
    is_staff:boolean;
    is_superuser:boolean;
    // token?:string;
}