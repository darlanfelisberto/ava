import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { QuestaoDTO, AlternativaDTO } from '../model';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';

@Component({
  selector: 'app-alternativa-unic',
  standalone: true,
  imports: [CommonModule, FormsModule, ButtonModule, InputTextModule],
  template: `
    @for (alternativa of questao.listaAlternativa; track alternativa) {
      <div class="alternativa">
        <input type="radio" [name]="'alternativa_' + questao.idQuestao" [disabled]="true" />
        <input pInputText type="text" [(ngModel)]="alternativa.descricao" placeholder="Alternativa" class="title-input" />
        <p-button icon="pi pi-trash" (click)="removerAlternativa(alternativa)" styleClass="p-button-danger p-button-text"></p-button>
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
    .alternativa input[type="radio"] {
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
export class AlternativaUnicComponent {
  @Input() questao!: QuestaoDTO;

  adicionarAlternativa() {
    this.questao.listaAlternativa?.push({} as AlternativaDTO);
  }

  removerAlternativa(alternativa: AlternativaDTO) {
    this.questao.listaAlternativa = this.questao.listaAlternativa?.filter(a => a !== alternativa);
  }
}
