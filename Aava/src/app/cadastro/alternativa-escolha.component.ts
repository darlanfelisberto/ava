import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { QuestaoDTO, AlternativaDTO, TipoQuestao } from '../model';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { ValidacaoInputComponent } from '../componentes/validacao-input.component';

@Component({
  selector: 'app-alternativa-escolha',
  standalone: true,
  imports: [CommonModule, FormsModule, ButtonModule, InputTextModule, ValidacaoInputComponent],
  template: `
    @for (alternativa of questao.listaAlternativa; track alternativa; let i = $index) {
      <div class="alternativa">
        @if (tipo === TipoQuestao.unic) {
          <input type="radio" [name]="'alternativa_' + questao.idQuestao" [disabled]="true" />
        } @else {
          <input type="checkbox" [disabled]="true" />
        }
        <input pInputText type="text" [(ngModel)]="alternativa.descricao" placeholder="Alternativa" class="title-input"
               name="alternativa_{{pageIndex}}_{{questionIndex}}_{{i}}" required #altField="ngModel" />
        <p-button icon="pi pi-trash" (click)="removerAlternativa(alternativa)" styleClass="p-button-danger p-button-text"></p-button>
        <app-validacao-input [control]="altField.control" nomeDoCampo="Alternativa"></app-validacao-input>
      </div>
    }
    <p-button icon="pi pi-plus" (click)="adicionarAlternativa()" styleClass="p-button-text add-button"></p-button>
  `,
  styles: [`
    .alternativa {
      display: flex;
      align-items: center;
      margin-top: 0.5rem;
    }
    .alternativa input[type="radio"], .alternativa input[type="checkbox"] {
      margin-right: 0.5rem;
    }
    .alternativa p-button {
      visibility: hidden;
    }
    .alternativa:hover p-button {
      visibility: visible;
    }
    .add-button {
      visibility: hidden;
    }
    :host-context(.card:hover) .add-button {
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
  `]
})
export class AlternativaEscolhaComponent {
  @Input() questao!: QuestaoDTO;
  @Input() tipo!: TipoQuestao.unic | TipoQuestao.mult;
  @Input() pageIndex!: number;
  @Input() questionIndex!: number;
  @Output() alternativaAdicionada = new EventEmitter<{ pageIndex: number, questionIndex: number, altIndex: number }>();

  TipoQuestao = TipoQuestao;

  adicionarAlternativa() {
    this.questao.listaAlternativa?.push({} as AlternativaDTO);
    const newAltIndex = (this.questao.listaAlternativa?.length ?? 0) - 1;
    this.alternativaAdicionada.emit({ pageIndex: this.pageIndex, questionIndex: this.questionIndex, altIndex: newAltIndex });
  }

  removerAlternativa(alternativa: AlternativaDTO) {
    this.questao.listaAlternativa = this.questao.listaAlternativa?.filter(a => a !== alternativa);
  }
}
