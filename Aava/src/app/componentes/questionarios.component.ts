import { Component, OnInit, inject } from '@angular/core';
import { QuestionarioDTO } from '../model';
import { RouterLink } from '@angular/router';
import { QuestionarioService } from '../services/questionario.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-questionarios',
  standalone: true,
  imports: [RouterLink, CommonModule],
  template: `
    <h2>Meus Questionários</h2>

    <ul>
      @for (questionario of questionarios; track questionario.idQuestionario) {
        <li>
          <a [routerLink]="['/questionario', questionario.idQuestionario]">{{ questionario.descricao }}</a>
        </li>
      }
    </ul>
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
  questionarios: QuestionarioDTO[] = [];
  private questionarioService = inject(QuestionarioService);

  ngOnInit(): void {
    this.questionarioService.getAll().subscribe(data => {
      this.questionarios = data;
    });
  }
}
