import { Examen } from "./examen";
import { Generic } from "./generic";

export class Alumno  implements Generic{

    id!:number;
    nombre!:string;
    apellido!:string;
    email!:string;
    createAt!:string;
    alumnos: Alumno[] =[];
    examenes:Examen[]=[];
}
