import { Routes } from '@angular/router';
import { QuestionarioComponent } from './componentes/questionario.component';
import { QuestionariosComponent } from './componentes/questionarios.component';
import { authGuard } from './auth.guard';
import { CadastroQuestionarioComponent } from './cadastro/cadastro-questionario.component';
import { EditarQuestionarioComponent } from './cadastro/editar-questionario.component';
import {MeusQuestionariosComponent} from './cadastro/MeusQuestionarios.component';

export const routes: Routes = [
    {
      path: 'questionario/editar/:id',
      component: EditarQuestionarioComponent,
      // canActivate: [authGuard]
    },
    {
      path: 'questionario/novo',
      component: CadastroQuestionarioComponent,
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
      path: 'meus',
      component: MeusQuestionariosComponent,
      // canActivate: [authGuard]
    },
    {
      path: '',
      component: MeusQuestionariosComponent,
      // canActivate: [authGuard]
    },
];
