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

  changeRespAlte(event: {alternativa: AlternativaDTO, checked: boolean}) {
    if (!this.respostaQuestao) {
      this.respostaQuestao = {};
    }
    if (!this.respostaQuestao.listaRespostaAlternativa) {
      this.respostaQuestao.listaRespostaAlternativa = [];
    }

    if (!this.respostaQuestao.idQuestao && this.questao && this.questao.idQuestao) {
      this.respostaQuestao.idQuestao = this.questao.idQuestao;
    }

    switch (this.questao?.tipoQuestao) {
      case TipoQuestao.unic:
        this.respostaQuestao.listaRespostaAlternativa = [];
        const novaRespostaAlternativaUnic: RespostaAlternativaDTO = {};
        novaRespostaAlternativaUnic.IdAlternativa = event.alternativa.idAlternativa;
        this.respostaQuestao.listaRespostaAlternativa.push(novaRespostaAlternativaUnic);
        break;
      case TipoQuestao.mult:
        if (event.checked) {
          const novaRespostaAlternativaMult: RespostaAlternativaDTO = {};
          novaRespostaAlternativaMult.IdAlternativa = event.alternativa.idAlternativa;
          this.respostaQuestao.listaRespostaAlternativa.push(novaRespostaAlternativaMult);
        } else {
          this.respostaQuestao.listaRespostaAlternativa = this.respostaQuestao.listaRespostaAlternativa.filter(
            resp => resp.IdAlternativa !== event.alternativa.idAlternativa
          );
        }
        break;
    }

    this.respostaQuestaoChange.emit(this.respostaQuestao);
  }

  protected readonly TipoQuestao = TipoQuestao;
}
