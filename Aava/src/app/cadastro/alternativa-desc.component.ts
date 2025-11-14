import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {Textarea} from 'primeng/textarea';

@Component({
  selector: 'app-alternativa-desc',
  standalone: true,
  imports: [CommonModule, Textarea],
  template: `
    <textarea rows="5" pTextarea [autoResize]="true" placeholder="Insira uma Resposta" class="w-full" [disabled]="true"></textarea>
  `,
})
export class AlternativaDescComponent {
}
