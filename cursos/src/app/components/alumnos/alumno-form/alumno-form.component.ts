import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Alumno } from 'src/app/models/alumno';
import { AlumnoService } from 'src/app/services/alumno.service';

@Component({
  selector: 'app-alumno-form',
  templateUrl: './alumno-form.component.html',
  styleUrls: ['./alumno-form.component.css'],
})
export class AlumnoFormComponent implements OnInit {
  titulo = 'Crear Alumno';
  alumno: Alumno = new Alumno();

  error: any;

  constructor(
    private alumnoService: AlumnoService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      const id: number = +params.get('id')!;
      if (id) {
        this.alumnoService
          .ver(id)
          .subscribe((alumno) => (this.alumno = alumno));
      }
    });
  }

  crearAlumno(): void {
    this.alumnoService.crear(this.alumno).subscribe(
      (alumno) => {
        console.log(alumno);
        alert('alumno creado' + alumno.nombre);
        this.router.navigate(['/alumnos']);
      },
      (err) => {
        if (err.status === 400) {
          this.error = err.error;
          console.log(this.error);
        }
      }
    );
  }

  editarAlumno(): void {
    this.alumnoService.editar(this.alumno).subscribe(
      (alumno) => {
        console.log(alumno);
        alert('alumno actualizado ' + alumno.nombre);
        this.router.navigate(['/alumnos']);
      },
      (err) => {
        if (err.status === 400) {
          this.error = err.error;
          console.log(this.error);
        }
      }
    );
  }

}
