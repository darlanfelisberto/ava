
import { Component, inject, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { PaginaDTO, QuestionarioDTO } from '../model';
import { QuestionarioService } from '../services/questionario.service';
import { ValidacaoInputComponent } from '../componentes/validacao-input.component';
import { CadastroPaginaComponent } from './cadastro-pagina.component';
import { ButtonModule } from 'primeng/button';
import { TooltipModule } from 'primeng/tooltip';

@Component({
  selector: 'app-cadastro-questionario',
  standalone: true,
  imports: [CommonModule, FormsModule, ValidacaoInputComponent, CadastroPaginaComponent, ButtonModule, TooltipModule],
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
        <app-validacao-input [control]="nomeField.control" nomeDoCampo="Nome do Questionário"></app-validacao-input>

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
        <app-validacao-input [control]="descricaoField.control" nomeDoCampo="Descrição do Questionário"></app-validacao-input>

        <div class="pages-section">
          @for (pagina of questionario.paginas; track pagina; let i = $index) {
            <app-cadastro-pagina [pagina]="pagina" (removerPagina)="removerPagina($event)" [pageIndex]="i" [totalPreviousQuestions]="getTotalPreviousQuestions(i)" (questaoAdicionada)="onQuestaoAdicionada($event)" (alternativaAdicionada)="onAlternativaAdicionada($event)"></app-cadastro-pagina>
          }
        </div>

        <div class="botoes-form">
            <p-button (click)="adicionarPagina()" icon="pi pi-plus" pTooltip="Nova Página" tooltipPosition="left"></p-button>
            <p-button type="submit" icon="pi pi-save" [disabled]="!questionarioForm.form.valid" pTooltip="Salvar Questionário" tooltipPosition="left"></p-button>
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
export class CadastroQuestionarioComponent {
  @ViewChild('questionarioForm') questionarioForm!: NgForm;
  questionario: QuestionarioDTO = { paginas: [] };

  private questionarioService = inject(QuestionarioService);
  private router = inject(Router);

  autoGrow(event: any): void {
    const element = event.target;
    element.style.height = 'auto';
    element.style.height = (element.scrollHeight) + 'px';
  }

  onSubmit(form: NgForm): void {
    if (form.invalid) {
      Object.values(form.controls).forEach(control => {
        control.markAsTouched();
      });
      return;
    }

    console.log(this.questionario);
    // TODO: Implementar a lógica de salvar o questionário
  }

  adicionarPagina(): void {
    this.questionario.paginas?.push({ questoes: [] } as PaginaDTO);
    setTimeout(() => {
      const newPageIndex = (this.questionario.paginas?.length ?? 0) - 1;
      const nomePaginaControl = this.questionarioForm.form.get(`nomePagina_${newPageIndex}`);
      const descPaginaControl = this.questionarioForm.form.get(`descricaoPagina_${newPageIndex}`);
      nomePaginaControl?.markAsTouched();
      descPaginaControl?.markAsTouched();
    });
  }

  removerPagina(pagina: PaginaDTO): void {
    this.questionario.paginas = this.questionario.paginas?.filter(p => p !== pagina);
  }

  onQuestaoAdicionada(event: { pageIndex: number, questionIndex: number }) {
    setTimeout(() => {
      const descQuestaoControl = this.questionarioForm.form.get(`descricaoQuestao_${event.pageIndex}_${event.questionIndex}`);
      const tipoQuestaoControl = this.questionarioForm.form.get(`tipoQuestao_${event.pageIndex}_${event.questionIndex}`);
      descQuestaoControl?.markAsTouched();
      tipoQuestaoControl?.markAsTouched();
    });
  }

  onAlternativaAdicionada(event: { pageIndex: number, questionIndex: number, altIndex: number }) {
    setTimeout(() => {
      const altControl = this.questionarioForm.form.get(`alternativa_${event.pageIndex}_${event.questionIndex}_${event.altIndex}`);
      altControl?.markAsTouched();
    });
  }

  getTotalPreviousQuestions(pageIndex: number): number {
    let total = 0;
    for (let i = 0; i < pageIndex; i++) {
      // @ts-ignore
      total += this.questionario.paginas[i].questoes?.length ?? 0;
    }
    return total;
  }
}
