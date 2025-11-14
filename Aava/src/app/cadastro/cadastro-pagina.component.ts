
import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
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
  imports: [CommonModule, FormsModule, CadastroQuestaoComponent, ButtonModule, MenuModule,  InputTextModule, ValidacaoInputComponent],
  template: `
    <div class="pagina-card">
      <div class="pagina-actions">
        <p-button icon="pi pi-trash" (click)="removerPagina.emit(pagina)" styleClass="p-button-danger p-button-text"></p-button>
      </div>
      <div class="pagina-header">
        <span class="page-index">{{pageIndex + 1}}.</span>
        <input
          type="text"
          class="title-input"
          placeholder="Nome da Página"
          [(ngModel)]="pagina.nome"
          name="nomePagina_{{pageIndex}}"
          required
          #nomePaginaField="ngModel"
        />
        <app-validacao-input [control]="nomePaginaField.control" nomeDoCampo="Nome da Página"></app-validacao-input>
      </div>
      <textarea
        class="desc-input"
        placeholder="Descrição da Página"
        [(ngModel)]="pagina.descricao"
        name="descricaoPagina_{{pageIndex}}"
        rows="1"
        required
        #descPaginaField="ngModel"
      ></textarea>
      <app-validacao-input [control]="descPaginaField.control" nomeDoCampo="Descrição da Página"></app-validacao-input>

      <div class="mt-1">
        @for (questao of pagina.questoes; track questao; let i = $index) {
          <app-cadastro-questao [questao]="questao" (removerQuestao)="removerQuestao(questao)" [pageIndex]="pageIndex" [questionIndex]="i" [totalPreviousQuestions]="totalPreviousQuestions" (alternativaAdicionada)="onAlternativaAdicionada($event)"></app-cadastro-questao>
        }
      </div>

      <div class="pagina-footer">
        <p-button icon="pi pi-plus-circle" (click)="adicionarQuestao()" styleClass="p-button-text" title="Adicionar Questão"></p-button>
        <p-button icon="pi pi-search" styleClass="p-button-text" title="Buscar Questão"></p-button>
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
  @Input() pagina: PaginaDTO = { questoes: [] };
  @Input() pageIndex!: number;
  @Input() totalPreviousQuestions!: number;
  @Output() removerPagina = new EventEmitter<PaginaDTO>();
  @Output() questaoAdicionada = new EventEmitter<{ pageIndex: number, questionIndex: number }>();
  @Output() alternativaAdicionada = new EventEmitter<{ pageIndex: number, questionIndex: number, altIndex: number }>();

  adicionarQuestao(): void {
    this.pagina.questoes?.push({ resposta: [], listaAlternativa: [] } as QuestaoDTO);
    const newQuestionIndex = (this.pagina.questoes?.length ?? 0) - 1;
    this.questaoAdicionada.emit({ pageIndex: this.pageIndex, questionIndex: newQuestionIndex });
  }

  removerQuestao(questao: QuestaoDTO) {
    this.pagina.questoes = this.pagina.questoes?.filter(q => q !== questao);
  }

  onAlternativaAdicionada(event: { pageIndex: number, questionIndex: number, altIndex: number }) {
    this.alternativaAdicionada.emit(event);
  }
}
