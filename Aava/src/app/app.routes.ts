import { Routes } from '@angular/router';
import { QuestionarioComponent } from './componentes/questionario.component';
import { QuestionariosComponent } from './componentes/questionarios.component';
import { authGuard } from './auth.guard';
import {NovoQuestionarioComponent} from './componentes/novo-questionario.component';

export const routes: Routes = [
    {
      path: 'questionario/novo',
      component: NovoQuestionarioComponent,
      // canActivate: [authGuard]
    },
    {
        path: 'questionario/:id',
        component: QuestionarioComponent,
        // canActivate: [authGuard]
    },
    {
        path: 'questionarios',
        component: QuestionariosComponent,
        // canActivate: [authGuard]
    },
    {
      path: '',
      component: QuestionariosComponent,
      // canActivate: [authGuard]
    }
];
