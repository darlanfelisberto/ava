import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { QuestaoDTO } from '../model';

@Component({
  selector: 'app-alternativa-desc',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <textarea pInputTextarea placeholder="Resposta descritiva" [disabled]="true"></textarea>
  `,
})
export class AlternativaDescComponent {
  @Input() questao!: QuestaoDTO;
}
