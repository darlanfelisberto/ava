import {Component, EventEmitter, Input, Output} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AlternativaDTO, QuestaoDTO, RespostaQuestaoDTO, TipoQuestao} from '../model';
import {AlternativaComponent} from './alternativa.component';

@Component({
  selector: 'app-questao',
  standalone: true,
  imports: [CommonModule, AlternativaComponent],
  template: `
    @if (questao && questao.listaAlternativa) {
      <h3>{{ questao.descricao }}</h3>
      <app-alternativa
        [listaAlternativa]="questao.listaAlternativa"
        [questao]="questao"
        [respostaQuestao]="respostaQuestao"
        (changeRespAlte)="changeRespAlte($event)">
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

  @Output() respostaQuestaoChange = new EventEmitter<{idQuestao?: number, idAlternativa?: number, texto?: string, selecionada: boolean}>();

  changeRespAlte(event: {alternativa: AlternativaDTO}) {

    if(this.questao?.tipoQuestao === TipoQuestao.unic){

    }


    console.log(event)
  }
}
