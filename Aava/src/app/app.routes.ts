import { Routes } from '@angular/router';
import { QuestionarioComponent } from './componentes/questionario.component';
import { QuestionariosComponent } from './componentes/questionarios.component';

export const routes: Routes = [
    { path: 'questionario/:id', component: QuestionarioComponent },
    { path: 'questionarios', component: QuestionariosComponent }
];
