
import { Component, inject, ViewChild, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { PaginaDTO, QuestionarioDTO } from '../model';
import { QuestionarioService } from '../services/questionario.service';
import { ValidacaoInputComponent } from '../componentes/validacao-input.component';
import { CadastroPaginaComponent } from './cadastro-pagina.component';
import { ButtonModule } from 'primeng/button';
import { TooltipModule } from 'primeng/tooltip';

@Component({
  selector: 'app-cadastro-questionario',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, ValidacaoInputComponent, CadastroPaginaComponent, ButtonModule, TooltipModule],
  template: `
    <div class="container">
      <form [formGroup]="questionarioForm" (ngSubmit)="onSubmit()">
        <input
          type="text"
          class="title-input"
          placeholder="Nome do Questionário"
          formControlName="nome"
        />
        <app-validacao-input [control]="questionarioForm.get('nome')" nomeDoCampo="Nome do Questionário"></app-validacao-input>

        <textarea
          class="desc-input"
          placeholder="Descrição do Questionário"
          formControlName="descricao"
          rows="1"
          (input)="autoGrow($event)"
        ></textarea>
        <app-validacao-input [control]="questionarioForm.get('descricao')" nomeDoCampo="Descrição do Questionário"></app-validacao-input>

        <div class="pages-section" formArrayName="paginas">
          @for (pagina of paginaControls; track pagina; let i = $index) {
            <app-cadastro-pagina [paginaFormGroup]="pagina" [pageIndex]="i" (removerPagina)="removerPagina(i)"></app-cadastro-pagina>
          }
        </div>

        <div class="botoes-form">
            <p-button (click)="adicionarPagina()" icon="pi pi-plus" pTooltip="Nova Página" tooltipPosition="left" type="button"></p-button>
            <p-button type="submit" icon="pi pi-save" [disabled]="!questionarioForm.valid" pTooltip="Salvar Questionário" tooltipPosition="left"></p-button>
        </div>
      </form>
    </div>
  `,
  styles: [
    `
      .container {
        padding: 4rem;
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
      ::placeholder {
        color: #adb5bd;
        opacity: 1; /* Firefox */
      }

      ::-ms-input-placeholder { /* Edge */
        color: #adb5bd;
      }
      .pages-section {
        margin-top: 2rem;
      }
      .botoes-form {
        position: fixed;
        bottom: 2rem;
        right: 2rem;
        z-index: 1000;
        display: flex;
        flex-direction: column;
        gap: 0.5rem;
      }
    `
  ]
})
export class CadastroQuestionarioComponent implements OnInit {
  questionarioForm!: FormGroup;

  private fb = inject(FormBuilder);
  private questionarioService = inject(QuestionarioService);
  private router = inject(Router);

  ngOnInit(): void {
    this.questionarioForm = this.fb.group({
      nome: ['', Validators.required],
      descricao: ['', Validators.required],
      paginas: this.fb.array([])
    });
  }

  get paginas() {
    return this.questionarioForm.get('paginas') as FormArray;
  }

  get paginaControls() {
    return (this.questionarioForm.get('paginas') as FormArray).controls as FormGroup[];
  }

  autoGrow(event: any): void {
    const element = event.target;
    element.style.height = 'auto';
    element.style.height = (element.scrollHeight) + 'px';
  }

  onSubmit(): void {
    if (this.questionarioForm.invalid) {
      this.questionarioForm.markAllAsTouched();
      return;
    }
    // TODO: Implementar a lógica de salvar o questionário
    console.log(this.questionarioForm.value);
    console.log(this.questionarioForm.getRawValue());
  }

  adicionarPagina(): void {
    const paginaForm = this.fb.group({
      nome: ['', Validators.required],
      descricao: ['', Validators.required],
      questoes: this.fb.array([])
    });
    this.paginas.push(paginaForm);
    paginaForm.markAllAsTouched();
  }

  removerPagina(index: number): void {
    this.paginas.removeAt(index);
  }

  getTotalPreviousQuestions(pageIndex: number): number {
    let total = 0;
    for (let i = 0; i < pageIndex; i++) {
      const page = this.paginas.at(i) as FormGroup;
      total += (page.get('questoes') as FormArray).length;
    }
    return total;
  }
}
