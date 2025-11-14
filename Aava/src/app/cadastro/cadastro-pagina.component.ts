
import { Component, Input, OnInit, Output, EventEmitter, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormGroup, FormArray, FormBuilder, Validators } from '@angular/forms';
import { PaginaDTO, QuestaoDTO } from '../model';
import { CadastroQuestaoComponent } from './cadastro-questao.component';
import { ButtonModule } from 'primeng/button';
import { MenuModule } from 'primeng/menu';
import { MenuItem } from 'primeng/api';
import { InputTextModule } from 'primeng/inputtext';
import { ValidacaoInputComponent } from '../componentes/validacao-input.component';

@Component({
  selector: 'app-cadastro-pagina',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, CadastroQuestaoComponent, ButtonModule, MenuModule,  InputTextModule, ValidacaoInputComponent],
  template: `
    <div class="pagina-card" [formGroup]="paginaFormGroup">
      <div class="pagina-actions">
        <p-button icon="pi pi-trash" (click)="removerPagina.emit()" styleClass="p-button-danger p-button-text" type="button"></p-button>
      </div>
      <div class="pagina-header">
        <span class="page-index">{{pageIndex + 1}}.</span>
        <input
          type="text"
          class="title-input"
          placeholder="Nome da Página"
          formControlName="nome"
        />
        <app-validacao-input [control]="paginaFormGroup.get('nome')" nomeDoCampo="Nome da Página"></app-validacao-input>
      </div>
      <textarea
        class="desc-input"
        placeholder="Descrição da Página"
        formControlName="descricao"
        rows="1"
      ></textarea>
      <app-validacao-input [control]="paginaFormGroup.get('descricao')" nomeDoCampo="Descrição da Página"></app-validacao-input>

      <div class="mt-1" formArrayName="questoes">
        @for (questao of questaoControls; track questao; let i = $index) {
          <app-cadastro-questao [questaoFormGroup]="questao" [pageIndex]="pageIndex" [questionIndex]="i" [totalPreviousQuestions]="totalPreviousQuestions" (removerQuestao)="removerQuestao(i)"></app-cadastro-questao>
        }
      </div>

      <div class="pagina-footer">
        <p-button icon="pi pi-plus-circle" (click)="adicionarQuestao()" styleClass="p-button-text" title="Adicionar Questão" type="button"></p-button>
        <p-button icon="pi pi-search" styleClass="p-button-text" title="Buscar Questão" type="button"></p-button>
      </div>
    </div>
  `,
  styles: [`
    .pagina-card {
      border: 1px solid transparent;
      padding: 1rem;
      margin-bottom: 1rem;
      border-radius: 5px;
      transition: border-color 0.3s;
    }
    .pagina-card:hover {
      border-color: #ccc;
    }
    .pagina-card .pagina-actions, .pagina-card .pagina-footer {
      visibility: hidden;
    }
    .pagina-card:hover .pagina-actions, .pagina-card:hover .pagina-footer {
      visibility: visible;
    }
    .pagina-actions {
      display: flex;
      justify-content: flex-end;
    }
    .pagina-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
    }
    .pagina-footer {
      display: flex;
      justify-content: center;
      align-items: center;
      margin-top: 1rem;
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
      .title-input {
        font-size: 1.5rem;
        font-weight: 500;
        line-height: 1.2;
        color: #212529;
      }
      .desc-input {
        font-size: 1rem;
        resize: none;
        overflow: hidden;
        color: #495057;
      }
    .page-index {
      font-size: 1.5rem;
      font-weight: 500;
      line-height: 1.2;
      color: #212529;
      margin-right: 0.5rem;
    }
  `]
})
export class CadastroPaginaComponent {
  @Input() paginaFormGroup!: FormGroup;
  @Input() pageIndex!: number;
  @Input() totalPreviousQuestions!: number;
  @Output() removerPagina = new EventEmitter<void>();

  private fb = inject(FormBuilder);

  get questoes() {
    return this.paginaFormGroup.get('questoes') as FormArray;
  }

  get questaoControls() {
    return (this.paginaFormGroup.get('questoes') as FormArray).controls as FormGroup[];
  }

  adicionarQuestao(): void {
    const questaoForm = this.fb.group({
      descricao: ['', Validators.required],
      tipoQuestao: ['', Validators.required],
      listaAlternativa: this.fb.array([])
    });
    this.questoes.push(questaoForm);
    questaoForm.markAllAsTouched();
  }

  removerQuestao(index: number) {
    this.questoes.removeAt(index);
  }
}
