import { Examen } from "./examen";

export class Alumno {

    id!:number;
    nombre!:string;
    apellido!:string;
    email!:string;
    createAt!:string;
    alumnos: Alumno[] =[];
    examenes:Examen[]=[];
}
