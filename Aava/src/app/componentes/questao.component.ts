import {Component, EventEmitter, Input, Output} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AlternativaDTO, QuestaoDTO, RespostaAlternativaDTO, RespostaQuestaoDTO, TipoQuestao} from '../model';
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
        [nomeMesclar]="questao.tipoQuestao === TipoQuestao.mult"
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

  @Output() respostaQuestaoChange = new EventEmitter<RespostaQuestaoDTO>();

  changeRespAlte(event: {alternativa: AlternativaDTO}) {

    if(this.questao?.tipoQuestao === TipoQuestao.unic){
      if (!this.respostaQuestao) {
        this.respostaQuestao = {};
        this.respostaQuestao.listaRespostaAlternativa = [];
      }

      if (!this.respostaQuestao.idQuestao && this.questao.idQuestao) {
        this.respostaQuestao.idQuestao = this.questao.idQuestao;
      }

      // Clear previous selections for unic type
      this.respostaQuestao.listaRespostaAlternativa = [];

      // Add the new selection
      const novaRespostaAlternativa:RespostaAlternativaDTO = {};
      novaRespostaAlternativa.IdAlternativa = event.alternativa.idAlternativa;
      this.respostaQuestao.listaRespostaAlternativa.push(novaRespostaAlternativa);

      this.respostaQuestaoChange.emit(this.respostaQuestao);
    }


    console.log(event)
  }

  protected readonly TipoQuestao = TipoQuestao;
}
