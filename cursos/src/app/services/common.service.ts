import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Generic } from '../models/generic';

@Injectable({
  providedIn: 'root'
})
export abstract class CommonService<E extends Generic> {

  protected baseEndpoint!:string;
  protected cabeceras: HttpHeaders =new HttpHeaders({'Content-Type': 'application/json'})

  constructor(protected http:HttpClient) { }

  //listar alumnos
  public listar(): Observable<E[]>{
    return this.http.get(this.baseEndpoint).pipe(
      map(alumnos =>  alumnos as E[])
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
  public ver(id:number):Observable<E>{
    return this.http.get(this.baseEndpoint+id).pipe(
      map(alumno=> alumno as E)
    );
  }

  //guardar alumno 
  public crear(e:E):Observable<E>{
    return this.http.post(this.baseEndpoint,e,{headers:this.cabeceras}).pipe(
      map(alumno => alumno as E)
    );
  }
  //editar alumno
  public editar(e:E):Observable<E>{
    return this.http.put(this.baseEndpoint+"/"+e.id,e,{headers:this.cabeceras}).pipe(
      map(alumno => alumno as E)
    );
  }

  //eliminar alumno
  public eliminar(id:number):Observable<void>{
    return this.http.delete<void>(this.baseEndpoint+"/"+id)
  }

  





}
