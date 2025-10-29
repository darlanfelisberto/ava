import { Component, Input } from '@angular/core';
import { QuestionarioDTO } from '../model';

@Component({
  selector: 'app-questionario',
  standalone: true,
  imports: [],
  template: `
    @if (questionario) {
      <h2>{{ questionario.descricao }}</h2>

      @for (questao of questionario.listaQuestao; track questao.idQuestao) {
        <h3>{{ questao.descricao }}</h3>

        @switch (questao.tipoQuestao?.nome) {
          @case ('dissertativa') {
            <textarea></textarea>
          }
          @case ('multipla_escolha') {
            @for (alternativa of questao.listaAlternativa; track alternativa.idAlternativa) {
              <input type="radio" [name]="questao.idQuestao?.toString()" [value]="alternativa.idAlternativa">
              <label>{{ alternativa.descricao }}</label>
            }
          }
          @case ('unica_escolha') {
            @for (alternativa of questao.listaAlternativa; track alternativa.idAlternativa) {
              <input type="checkbox" [name]="questao.idQuestao?.toString()" [value]="alternativa.idAlternativa">
              <label>{{ alternativa.descricao }}</label>
            }
          }
        }
      }
    }

  `,
  styles: `

  `
})
export class QuestionarioComponent {
  @Input() questionario: QuestionarioDTO | undefined;
}
