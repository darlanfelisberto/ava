
import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { QuestaoDTO, TipoQuestao, AlternativaDTO } from '../model';
import { Select } from 'primeng/select';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { AlternativaDescComponent } from './alternativa-desc.component';
import { AlternativaUnicComponent } from './alternativa-unic.component';
import { AlternativaMultComponent } from './alternativa-mult.component';

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
    AlternativaUnicComponent,
    AlternativaMultComponent
  ],
  template: `
    <div class="card">
      <div class="card-header">
        <p-button icon="pi pi-trash" styleClass="p-button-danger p-button-text" title="Excluir" (click)="removerQuestao.emit()"></p-button>
      </div>
      <div class="card-main pt-4 pb-4">
          <input
            pInputText
            type="text"
            class="title-input"
            placeholder="Digite sua pergunta"
            [(ngModel)]="questao.descricao"
            name="descricaoQuestao_{{questao.idQuestao}}"
          />
        <div >
          @switch (questao.tipoQuestao) {
            @case (TipoQuestao.desc) {
              <app-alternativa-desc [questao]="questao"></app-alternativa-desc>
            }
            @case (TipoQuestao.unic) {
              <app-alternativa-unic [questao]="questao"></app-alternativa-unic>
            }
            @case (TipoQuestao.mult) {
              <app-alternativa-mult [questao]="questao"></app-alternativa-mult>
            }
          }
        </div>
      </div>

      <div class="flex justify-items-star">
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
}
