import { Component, OnInit } from '@angular/core';
import { Alumno } from 'src/app/models/alumno';
import { AlumnoService } from 'src/app/services/alumno.service';

@Component({
  selector: 'app-alumnos',
  templateUrl: './alumnos.component.html',
  styleUrls: ['./alumnos.component.css']
})
export class AlumnosComponent implements OnInit {

  titulo:string='listado de alumnos';
  alumnos!:Alumno[];

  constructor(private alumnoService:AlumnoService ) { }


  ngOnInit(): void {
    this.alumnoService.listar().subscribe( alumno =>{
       console.log(alumno);
       this.alumnos=alumno;
    });
  }

  eliminar(alumno: Alumno): void {
    
    if(confirm(`seguro que desea eliminar a ${alumno.nombre} ?`)){
      this.alumnoService.eliminar(alumno.id).subscribe(()=>{
          this.alumnos = this.alumnos.filter(a => a !==alumno)
          alert(`alumno ${alumno.nombre} eliminado con exito`)
      })
    }

  }
}
