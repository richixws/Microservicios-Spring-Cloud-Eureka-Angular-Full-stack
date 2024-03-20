import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Alumno } from '../models/alumno';

@Injectable({
  providedIn: 'root'
})
export class AlumnoService {

  private baseEndpoint='http://localhost:8090/api/alumnos/api/';
  private cabeceras: HttpHeaders =new HttpHeaders({'Content-Type': 'application/json'})

  constructor(private http:HttpClient) { }

  //listar alumnos
  public listar(): Observable<Alumno[]>{
    return this.http.get(this.baseEndpoint).pipe(
      map(alumnos =>  alumnos as Alumno[])
    );
  }
  //listar por pagina
  public listarPaginas(page: string , size: string): Observable<any>{
     const params=new HttpParams()
     .set('page',page)
     .set('size',size);
     return this.http.get<any>(this.baseEndpoint+"/pagina",{params:params});
  }

  //listar alumno por id
  public ver(id:number):Observable<Alumno>{
    return this.http.get(this.baseEndpoint+"/id").pipe(
      map(alumno=> alumno as Alumno)
    );
  }

  //guardar alumno 
  public crear(alumno:Alumno):Observable<Alumno>{
    return this.http.post(this.baseEndpoint,alumno,{headers:this.cabeceras}).pipe(
      map(alumno => alumno as Alumno)
    );
  }
  //editar alumno
  public editar(alumno:Alumno):Observable<Alumno>{
    return this.http.put(this.baseEndpoint+"/"+alumno.id,alumno,{headers:this.cabeceras}).pipe(
      map(alumno => alumno as Alumno)
    );
  }

  //eliminar alumno
  public eliminar(id:number):Observable<void>{
    return this.http.delete<void>(this.baseEndpoint+"/"+id)
  }

  





}
