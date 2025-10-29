import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AlternativaDTO } from '../model';

@Component({
  selector: 'app-alternativa',
  standalone: true,
  imports: [CommonModule],
  template: `

        @for (alternativa of listaAlternativa; track alternativa.idAlternativa) {
          <div>
            <input type="radio" [name]="alternativa.idAlternativa" [value]="alternativa.idAlternativa">
            <label>{{ alternativa.descricao }}</label>
          </div>
        }
  `,
  styles: [`
    div {
      margin-bottom: 8px;
    }
  `]
})
export class AlternativaComponent {
  @Input() listaAlternativa: AlternativaDTO[] = [];
  @Input() tipoQuestao: string | undefined;
}
