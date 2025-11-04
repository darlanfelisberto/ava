import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AlternativaDTO, QuestaoDTO,  RespostaQuestaoDTO, TipoQuestao} from '../model';
import {AlternativaComponent} from './alternativa.component';

@Component({
  selector: 'app-questao',
  standalone: true,
  imports: [CommonModule, AlternativaComponent],
  template: `
    @if (questao && questao.listaAlternativa) {
      <h3>{{ questao.descricao }}</h3>
        <app-alternativa [questao]="questao"
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
export class QuestaoComponent implements OnInit{
  @Input() questao: QuestaoDTO | undefined;
  @Input() respostaQuestao: RespostaQuestaoDTO | undefined;

  @Output() respostaQuestaoChange = new EventEmitter<RespostaQuestaoDTO>();

  ngOnInit() {
    console.log(">>>>>>>>>>>>>>>>>>>>")
    console.log(this.respostaQuestao);
    console.log("ques")
    console.log(this.questao);
    console.log("<<<<<<<<<<<<<<<<<<<<")
  }

  changeRespAlte(event: {alternativa: AlternativaDTO, checked: boolean}) {
    if (!this.respostaQuestao) {
      this.respostaQuestao = {};
    }
    // if (!this.respostaQuestao.listaAlternativas) {
    //   this.respostaQuestao.listaAlternativas = [];
    // }
    //
    // if (!this.respostaQuestao.idQuestao && this.questao && this.questao.idQuestao) {
    //   this.respostaQuestao.idQuestao = this.questao.idQuestao;
    // }
    //
    // switch (this.questao?.tipoQuestao) {
    //   case TipoQuestao.unic:
    //     this.respostaQuestao.listaAlternativas = [];
    //     const novaRespostaAlternativaUnic: RespostaAlternativaDTO = {};
    //     novaRespostaAlternativaUnic.idAlternativa = event.alternativa.idAlternativa;
    //     this.respostaQuestao.listaAlternativas.push(novaRespostaAlternativaUnic);
    //     break;
    //   case TipoQuestao.mult:
    //     if (event.checked) {
    //       const novaRespostaAlternativaMult: RespostaAlternativaDTO = {};
    //       novaRespostaAlternativaMult.idAlternativa = event.alternativa.idAlternativa;
    //       this.respostaQuestao.listaAlternativas.push(novaRespostaAlternativaMult);
    //     } else {
    //       this.respostaQuestao.listaAlternativas = this.respostaQuestao.listaAlternativas.filter(
    //         resp => resp.idAlternativa !== event.alternativa.idAlternativa
    //       );
    //     }
    //     break;
    // }

    this.respostaQuestaoChange.emit(this.respostaQuestao);
  }

  protected readonly TipoQuestao = TipoQuestao;
}
