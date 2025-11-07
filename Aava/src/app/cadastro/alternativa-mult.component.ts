import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { QuestaoDTO, AlternativaDTO } from '../model';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';

@Component({
  selector: 'app-alternativa-mult',
  standalone: true,
  imports: [CommonModule, FormsModule, ButtonModule, InputTextModule],
  template: `
    @for (alternativa of questao.listaAlternativa; track alternativa) {
      <div class="alternativa">
        <input type="checkbox" [disabled]="true" />
        <input pInputText type="text" [(ngModel)]="alternativa.descricao" placeholder="Alternativa" />
        <p-button icon="pi pi-trash" (click)="removerAlternativa(alternativa)" styleClass="p-button-danger p-button-text"></p-button>
      </div>
    }
    <p-button icon="pi pi-plus" (click)="adicionarAlternativa()" styleClass="p-button-text"></p-button>
  `,
  styles: [`
    .alternativa {
      display: flex;
      align-items: center;
      margin-top: 0.5rem;
    }
    .alternativa input[type="checkbox"] {
      margin-right: 0.5rem;
    }
  `]
})
export class AlternativaMultComponent {
  @Input() questao!: QuestaoDTO;

  adicionarAlternativa() {
    this.questao.listaAlternativa?.push({} as AlternativaDTO);
  }

  removerAlternativa(alternativa: AlternativaDTO) {
    this.questao.listaAlternativa = this.questao.listaAlternativa?.filter(a => a !== alternativa);
  }
}
