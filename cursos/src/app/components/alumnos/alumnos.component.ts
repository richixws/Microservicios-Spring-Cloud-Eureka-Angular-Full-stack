import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { Alumno } from 'src/app/models/alumno';
import { AlumnoService } from 'src/app/services/alumno.service';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-alumnos',
  templateUrl: './alumnos.component.html',
  styleUrls: ['./alumnos.component.css']
})
export class AlumnosComponent implements OnInit {

  titulo:string='listado de alumnos';
  alumnos!:Alumno[];

  totalRegistros=0;
  paginaActual=0;
  totalPorPagina=4;

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private alumnoService:AlumnoService ) { }


  ngOnInit(): void {
    this.calcularRangos();
  }

  private calcularRangos(){
    this.alumnoService.listarPaginas(this.paginaActual.toString(),this.totalPorPagina.toString()).subscribe( p =>{
       console.log(p);
       this.alumnos=p.content as Alumno[];
       this.totalRegistros=p.totalElements as number;
       this.paginator._intl.itemsPerPageLabel='Registros por paguina:'
    });
  }

  paginar(event:PageEvent):void{
    this.paginaActual=event.pageIndex;
    this.totalPorPagina=event.pageSize;
    this.calcularRangos();
  }

  eliminar(alumno: Alumno): void {

    Swal.fire({
      title: "Cuidado:",
      text: `seguro que desea eliminar a ${alumno.nombre} ?`,
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Si, Eliminar!"
    }).then((result) => {
      if (result.isConfirmed) {
        this.alumnoService.eliminar(alumno.id).subscribe(()=>{
         // this.alumnos = this.alumnos.filter(a => a !==alumno)
         this.calcularRangos();
          Swal.fire('Eliminado:',`alumno ${alumno.nombre} eliminado con exito`,'success')
      })
      }
    });
  }
}
