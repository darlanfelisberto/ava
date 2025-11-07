
import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { QuestionarioDTO } from '../model';
import { QuestionarioService } from '../services/questionario.service';

@Component({
  selector: 'app-cadastro-questionario',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="container">
      <form #questionarioForm="ngForm" (ngSubmit)="onSubmit(questionarioForm)">
        <input
          type="text"
          class="title-input"
          placeholder="Nome do Questionário"
          [(ngModel)]="questionario.nome"
          name="nome"
          required
          #nomeField="ngModel"
        />
        @if (submitted && nomeField.invalid) {
          <div class="error-message">
            Nome do questionário é obrigatório.
          </div>
        }

        <textarea
          class="desc-input"
          placeholder="Descrição do Questionário"
          [(ngModel)]="questionario.descricao"
          name="descricao"
          rows="1"
          (input)="autoGrow($event)"
          required
          #descricaoField="ngModel"
        ></textarea>
        @if (submitted && descricaoField.invalid) {
          <div class="error-message">
            Descrição do questionário é obrigatória.
          </div>
        }
        <button type="submit">Salvar Questionário</button>
      </form>
    </div>
  `,
  styles: [
    `
      .container {
        padding: 2rem;
        max-width: 800px;
        margin: auto;
      }
      .title-input, .desc-input {
        width: 100%;
        border: none;
        background-color: transparent;
        outline: none;
        padding: 10px 0;
        border-bottom: 2px solid transparent;
        transition: border-bottom-color 0.3s;
        box-shadow: none;
      }
      .title-input:hover, .desc-input:hover,
      .title-input:focus, .desc-input:focus {
        border-bottom-color: #dee2e6;
      }
      .title-input.ng-invalid, .desc-input.ng-invalid {
        /* Highlight invalid fields only after submit attempt */
      }
      .submitted .title-input.ng-invalid, .submitted .desc-input.ng-invalid {
         border-bottom-color: red;
      }
      .title-input {
        font-size: 2.5rem;
        font-weight: 500;
        line-height: 1.2;
        color: #212529;
      }
      .desc-input {
        font-size: 1.25rem;
        resize: none;
        overflow: hidden;
        color: #495057;
      }
      .error-message {
        color: red;
        font-size: 0.875em;
        margin-top: -5px;
        margin-bottom: 10px;
      }
      button[type="submit"] {
        margin-top: 20px;
        padding: 10px 20px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
      }
    `
  ]
})
export class CadastroQuestionarioComponent {
  questionario: QuestionarioDTO = {};
  submitted = false;

  private questionarioService = inject(QuestionarioService);
  private router = inject(Router);

  autoGrow(event: any): void {
    const element = event.target;
    element.style.height = 'auto';
    element.style.height = (element.scrollHeight) + 'px';
  }

  onSubmit(form: NgForm): void {
    this.submitted = true;

    if (form.invalid) {
      console.log('Formulário Inválido!');
      return;
    }

  }
}
