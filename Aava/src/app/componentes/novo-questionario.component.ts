import { Component, OnInit, inject } from '@angular/core';
import { QuestionarioDTO } from '../model';
import { RouterLink } from '@angular/router';
import { QuestionarioService } from '../services/questionario.service';
import { CommonModule } from '@angular/common';
import {TableModule} from 'primeng/table';
import {Observable} from 'rxjs';
import {Button} from 'primeng/button';

@Component({
  selector: 'novo-questionario',
  standalone: true,
  imports: [ CommonModule, TableModule],
  template: `
    <div>
      <h2>
        <input type="text" class="fff" placeholder="Nome" required>
        <input type="text" class="fff" placeholder="Descrição" required>
      </h2>
    </div>
    <h1 class="text-3xl font-bold underline">
      Hello world!
    </h1>
  `,
  styles: [`
    .fff{
      border: 0;
    }
    .fff:hover{
      border: 2px solid red ;
    }
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
export class NovoQuestionarioComponent implements OnInit {
  questionario:QuestionarioDTO = {
  };
  private questionarioService = inject(QuestionarioService);

  ngOnInit(): void {

  }
}
