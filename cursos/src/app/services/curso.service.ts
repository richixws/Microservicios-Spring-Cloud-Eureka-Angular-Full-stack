import { Injectable } from '@angular/core';
import { CommonService } from './common.service';
import { Alumno } from '../models/alumno';

@Injectable({
  providedIn: 'root'
})
export class CursoService extends CommonService<Alumno> {

  protected override baseEndpoint='http://localhost:8090/api/alumnos/api/';
}
