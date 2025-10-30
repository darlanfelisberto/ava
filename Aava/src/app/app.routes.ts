import { Routes } from '@angular/router';
import { QuestionarioComponent } from './componentes/questionario.component';
import { QuestionariosComponent } from './componentes/questionarios.component';
import { authGuard } from './auth.guard';

export const routes: Routes = [
    {
        path: 'questionario/:id',
        component: QuestionarioComponent,
        // canActivate: [authGuard]
    },
    {
        path: 'questionarios',
        component: QuestionariosComponent,
        // canActivate: [authGuard]
    }
];
