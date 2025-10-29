import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { QuestaoDTO, RespostaQuestaoDTO } from '../model';
import { AlternativaComponent } from './alternativa.component';

@Component({
  selector: 'app-questao',
  standalone: true,
  imports: [CommonModule, AlternativaComponent],
  template: `
    @if (questao && questao.listaAlternativa) {
      <h3>{{ questao.descricao }}</h3>
      <app-alternativa
        [listaAlternativa]="questao.listaAlternativa"
        [tipoQuestao]="questao.tipoQuestao?.nome">
      </app-alternativa>
    }
  `,
  styles: [`
    div {
      margin-bottom: 8px;
    }
  `]
})
export class QuestaoComponent {
  @Input() questao: QuestaoDTO | undefined;
  @Input() respostaQuestao: RespostaQuestaoDTO | undefined;
}
