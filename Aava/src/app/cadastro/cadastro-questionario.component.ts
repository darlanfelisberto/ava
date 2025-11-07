
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
        @if (nomeField.invalid && (nomeField.dirty || nomeField.touched)) {
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
        @if (descricaoField.invalid && (descricaoField.dirty || descricaoField.touched)) {
          <div class="error-message">
            Descrição do questionário é obrigatória.
          </div>
        }
        <button type="submit" [disabled]="questionarioForm.invalid">Salvar Questionário</button>
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
      .title-input.ng-invalid.ng-touched, .desc-input.ng-invalid.ng-touched {
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
      button[type="submit"]:disabled {
        background-color: #cccccc;
        cursor: not-allowed;
      }
    `
  ]
})
export class CadastroQuestionarioComponent {
  questionario: QuestionarioDTO = {};

  private questionarioService = inject(QuestionarioService);
  private router = inject(Router);

  autoGrow(event: any): void {
    const element = event.target;
    element.style.height = 'auto';
    element.style.height = (element.scrollHeight) + 'px';
  }

  onSubmit(form: NgForm): void {
    if (form.invalid) {
      Object.keys(form.controls).forEach(field => {
        const control = form.control.get(field);
        control?.markAsTouched({ onlySelf: true });
      });
      console.log('Formulário Inválido!');
      return;
    }
  }
}
