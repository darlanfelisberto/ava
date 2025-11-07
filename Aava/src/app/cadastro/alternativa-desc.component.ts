import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { QuestaoDTO } from '../model';
import {Textarea} from 'primeng/textarea';

@Component({
  selector: 'app-alternativa-desc',
  standalone: true,
  imports: [CommonModule, FormsModule, Textarea],
  template: `
    <textarea rows="5" pTextarea [autoResize]="true" placeholder="Insira uma Resposta" class="w-full"></textarea>
  `,
})
export class AlternativaDescComponent {
  @Input() questao!: QuestaoDTO;
}
