
import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { QuestaoDTO, TipoQuestao, AlternativaDTO } from '../model';
import { Select } from 'primeng/select';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { AlternativaDescComponent } from './alternativa-desc.component';
import { AlternativaEscolhaComponent } from './alternativa-escolha.component';
import { ValidacaoInputComponent } from '../componentes/validacao-input.component';

@Component({
  selector: 'app-cadastro-questao',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    InputTextModule,
    ButtonModule,
    Select,
    AlternativaDescComponent,
    AlternativaEscolhaComponent,
    ValidacaoInputComponent
  ],
  template: `
    <div class="card">
      <div class="card-header">
        <p-button icon="pi pi-trash" styleClass="p-button-danger p-button-text" title="Excluir" (click)="removerQuestao.emit()"></p-button>
      </div>
      <div class="card-main pt-4 pb-4">
          <div class="flex">
            <span class="question-index">{{pageIndex + 1}}.{{totalPreviousQuestions + questionIndex + 1}}.</span>
            <input
              pInputText
              type="text"
              class="title-input"
              placeholder="Enunciado da Questão"
              [(ngModel)]="questao.descricao"
              name="descricaoQuestao_{{pageIndex}}_{{questionIndex}}"
              required
              #descQuestao="ngModel"
            />
          </div>
          <app-validacao-input [control]="descQuestao.control" nomeDoCampo="Enunciado da Questão"></app-validacao-input>
        <div >
          @switch (questao.tipoQuestao) {
            @case (TipoQuestao.desc) {
              <app-alternativa-desc [questao]="questao" ></app-alternativa-desc>
            }
            @case (TipoQuestao.unic) {
              <app-alternativa-escolha [questao]="questao" [tipo]="TipoQuestao.unic" [pageIndex]="pageIndex" [questionIndex]="questionIndex" (alternativaAdicionada)="onAlternativaAdicionada($event)"></app-alternativa-escolha>
            }
            @case (TipoQuestao.mult) {
              <app-alternativa-escolha [questao]="questao" [tipo]="TipoQuestao.mult" [pageIndex]="pageIndex" [questionIndex]="questionIndex" (alternativaAdicionada)="onAlternativaAdicionada($event)"></app-alternativa-escolha>
            }
          }
        </div>
      </div>

      <div class="card-footer flex justify-items-star">
        <p-select
          [options]="tiposQuestao"
          [(ngModel)]="questao.tipoQuestao"
          optionLabel="label"
          optionValue="value"
          placeholder="Tipo da Questão"
          (onChange)="onTipoQuestaoChange()"
          name="tipoQuestao_{{pageIndex}}_{{questionIndex}}"
          required
          #tipoQuestaoField="ngModel"
        ></p-select>
        <app-validacao-input [control]="tipoQuestaoField.control" nomeDoCampo="Tipo da Questão"></app-validacao-input>
      </div>
    </div>
  `,
  styles: [`
    .card {
      border: 1px solid #eee;
      padding: 1rem;
      margin-top: 1rem;
      border-radius: 5px;
      background-color: #f9f9f9;
    }
    .card-header {
      display: flex;
      justify-content: flex-end;
    }
    .card .card-header p-button, .card .card-footer {
      visibility: hidden;
    }
    .card:hover .card-header p-button, .card:hover .card-footer {
      visibility: visible;
    }
    .title-input {
        width: 100%;
        border: none;
        background-color: transparent;
        outline: none;
        padding: 10px 0;
        border-bottom: 2px solid transparent;
        transition: border-bottom-color 0.3s;
        box-shadow: none;
        font-size: 1rem;
        font-weight: 400;
        color: #212529;
      }
      .title-input:hover, .title-input:focus {
        border-bottom-color: #dee2e6;
      }
      ::placeholder {
        color: #adb5bd;
        opacity: 1; /* Firefox */
      }

      ::-ms-input-placeholder { /* Edge */
        color: #adb5bd;
      }
    .question-index {
      font-size: 1rem;
      font-weight: 400;
      color: #212529;
      margin-right: 0.5rem;
      padding-top: 10px;
    }
  `]
})
export class CadastroQuestaoComponent {
  @Input() questao: QuestaoDTO = { resposta: [], listaAlternativa: [] };
  @Input() pageIndex!: number;
  @Input() questionIndex!: number;
  @Input() totalPreviousQuestions!: number;
  @Output() removerQuestao = new EventEmitter<void>();
  @Output() alternativaAdicionada = new EventEmitter<{ pageIndex: number, questionIndex: number, altIndex: number }>();

  tiposQuestao = [
    { label: 'Descritiva', value: TipoQuestao.desc },
    { label: 'Única Escolha', value: TipoQuestao.unic },
    { label: 'Múltipla Escolha', value: TipoQuestao.mult }
  ];

  TipoQuestao = TipoQuestao;

  onTipoQuestaoChange() {
    if (this.questao.tipoQuestao === TipoQuestao.desc) {
      this.questao.listaAlternativa = [];
    } else if (!this.questao.listaAlternativa || this.questao.listaAlternativa.length === 0) {
      this.questao.listaAlternativa = [{} as AlternativaDTO];
    }
  }

  onAlternativaAdicionada(event: { pageIndex: number, questionIndex: number, altIndex: number }) {
    this.alternativaAdicionada.emit(event);
  }
}
