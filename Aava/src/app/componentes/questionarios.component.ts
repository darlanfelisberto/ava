import { Component, OnInit, inject } from '@angular/core';
import { QuestionarioDTO } from '../model';
import { RouterLink } from '@angular/router';
import { QuestionarioService } from '../services/questionario.service';
import { CommonModule } from '@angular/common';
import {TableModule} from 'primeng/table';
import {Observable} from 'rxjs';
import {Button} from 'primeng/button';

@Component({
  selector: 'app-questionarios',
  standalone: true,
  imports: [RouterLink, CommonModule, TableModule],
  template: `
    <h2>Minhas provas/questionários</h2>


    <p-table [value]="(this.questionarios$  | async) ?? []">
      <ng-template pTemplate="header">
        <tr>
          <th>ID</th>
          <th>Descrição</th>
          <th></th>
        </tr>
      </ng-template>
      <ng-template #body let-questionario>
        <tr>
          <td>{{ questionario.idQuestionario }}</td>
          <td>{{ questionario.descricao }}</td>
          <td>
            <a [routerLink]="['/questionario', questionario.idQuestionario]" class="p-ripple p-button p-component">Resposnder</a>
          </td>
        </tr>
      </ng-template>
    </p-table>

<!--    <ul>-->
<!--      @for (questionario of questionarios; track questionario.idQuestionario) {-->
<!--        <li>-->
<!--          <a [routerLink]="['/questionario', questionario.idQuestionario]">{{ questionario.descricao }}</a>-->
<!--        </li>-->
<!--      }-->
<!--    </ul>-->
  `,
  styles: [`
    ul {
      list-style-type: none;
      padding: 0;
    }
    li {
      margin: 8px 0;
    }
    a {
      text-decoration: none;
      color: #333;
    }
    a:hover {
      color: #007bff;
    }
  `]
})
export class QuestionariosComponent implements OnInit {
  questionarios$?: Observable<QuestionarioDTO[]>;
  private questionarioService = inject(QuestionarioService);

  ngOnInit(): void {
    this.questionarios$ = this.questionarioService.getAll();
  }
}
