import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AlumnosComponent } from './components/alumnos/alumnos.component';
import { CursosComponent } from './components/cursos/cursos.component';
import { ExamenesComponent } from './components/examenes/examenes.component';
import { AlumnoFormComponent } from './components/alumnos/alumno-form/alumno-form.component';

const routes: Routes = [
  {path: '',pathMatch:'full',redirectTo:'cursos'},
  {path: 'alumnos', component:AlumnosComponent},
  {path: 'alumnos/form', component:AlumnoFormComponent},
  {path: 'alumnos/form/:id', component:AlumnoFormComponent},
  {path: 'cursos',component:CursosComponent},
  {path: 'examenes',component:ExamenesComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
