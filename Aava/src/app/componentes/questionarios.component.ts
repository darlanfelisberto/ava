import { Component, OnInit } from '@angular/core';
import { Questionario } from '../model';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-questionarios',
  standalone: true,
  imports: [RouterLink],
  template: `
    <h2>Questionários</h2>

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
  questionarios: Questionario[] = [];

  // Injetar um serviço para buscar os questionários
  constructor() { }

  ngOnInit(): void {
    // Lógica para carregar os questionários - por enquanto, usaremos dados mocados
    this.questionarios = [
      { idQuestionario: 1, descricao: 'Questionário de Matemática' },
      { idQuestionario: 2, descricao: 'Questionário de Português' },
      { idQuestionario: 3, descricao: 'Questionário de História' }
    ];
  }
}
