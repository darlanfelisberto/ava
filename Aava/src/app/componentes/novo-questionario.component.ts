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
    <div class="flex flex-col">

        <input type="text" class="put" placeholder="Nome" required>
      <textarea class="put" placeholder="Descrição" name="story" rows="3" required></textarea>

    </div>
    <div style="padding: 50px" class="page">
        <h3>page 1</h3>

        <div>
            <main>
              asdasdsa
            </main>
          <ul>
            <li>d</li>
          </ul>
        </div>
    </div>
  `,
  styles: [`
    .put {
      border: 0;
    }

    .put:hover {
      border: 2px solid red;
    }

    .page:hover {
      border: 1px solid #686868;
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
