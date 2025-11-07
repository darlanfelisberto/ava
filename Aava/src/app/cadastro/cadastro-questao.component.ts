
import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { QuestaoDTO, TipoQuestao, AlternativaDTO } from '../model';
import {Select } from 'primeng/select';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';

@Component({
  selector: 'app-cadastro-questao',
  standalone: true,
  imports: [CommonModule, FormsModule, InputTextModule, ButtonModule, Select],
  template: `
    <div class="questao-card">
      <div class="questao-header">
        <div class="grid">
          <div class="col-8">
            <input
              pInputText
              type="text"
              class="title-input"
              placeholder="Digite sua pergunta"
              [(ngModel)]="questao.descricao"
              name="descricaoQuestao_{{questao.idQuestao}}"
            />
          </div>
          <div class="col-4">
            <p-select
              [options]="tiposQuestao"
              [(ngModel)]="questao.tipoQuestao"
              optionLabel="label"
              optionValue="value"
              placeholder="Tipo da Questão"
              (onChange)="onTipoQuestaoChange()"
            ></p-select>
          </div>
        </div>
      </div>

      <div class="questao-body">
        @switch (questao.tipoQuestao) {
          @case (TipoQuestao.desc) {
            <textarea pInputTextarea placeholder="Resposta descritiva" [disabled]="true"></textarea>
          }
          @case (TipoQuestao.unic) {
            @for (alternativa of questao.listaAlternativa; track alternativa) {
              <div class="alternativa">
                <input type="radio" [name]="'alternativa_' + questao.idQuestao" [disabled]="true" />
                <input pInputText type="text" [(ngModel)]="alternativa.descricao" placeholder="Alternativa" />
                <p-button icon="pi pi-trash" (click)="removerAlternativa(alternativa)" styleClass="p-button-danger p-button-text"></p-button>
              </div>
            }
            <p-button icon="pi pi-plus" (click)="adicionarAlternativa()" styleClass="p-button-text"></p-button>
          }
          @case (TipoQuestao.mult) {
            @for (alternativa of questao.listaAlternativa; track alternativa) {
              <div class="alternativa">
                <input type="checkbox" [disabled]="true" />
                <input pInputText type="text" [(ngModel)]="alternativa.descricao" placeholder="Alternativa" />
                <p-button icon="pi pi-trash" (click)="removerAlternativa(alternativa)" styleClass="p-button-danger p-button-text"></p-button>
              </div>
            }
            <p-button icon="pi pi-plus" (click)="adicionarAlternativa()" styleClass="p-button-text"></p-button>
          }
        }
      </div>

      <div class="questao-footer">
        <div class="questao-actions">
          <p-button icon="pi pi-copy" styleClass="p-button-text" title="Duplicar"></p-button>
          <p-button icon="pi pi-trash" styleClass="p-button-danger p-button-text" title="Excluir" (click)="removerQuestao.emit()"></p-button>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .questao-card {
      border: 1px solid #eee;
      padding: 1rem;
      margin-top: 1rem;
      border-radius: 5px;
      background-color: #f9f9f9;
    }
    .questao-header, .questao-footer {
      padding: 0.5rem 0;
    }
    .questao-footer {
      display: flex;
      justify-content: flex-end;
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
      .alternativa {
        display: flex;
        align-items: center;
        margin-top: 0.5rem;
      }
      .alternativa input[type="radio"], .alternativa input[type="checkbox"] {
        margin-right: 0.5rem;
      }
  `]
})
export class CadastroQuestaoComponent {
  @Input() questao: QuestaoDTO = { resposta: [], listaAlternativa: [] };
  @Output() removerQuestao = new EventEmitter<void>();

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

  adicionarAlternativa() {
    this.questao.listaAlternativa?.push({} as AlternativaDTO);
  }

  removerAlternativa(alternativa: AlternativaDTO) {
    this.questao.listaAlternativa = this.questao.listaAlternativa?.filter(a => a !== alternativa);
  }
}
