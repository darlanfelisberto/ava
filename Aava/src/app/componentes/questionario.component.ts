import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuestionarioDTO, RespostaQuestaoDTO } from '../model';
import { QuestionarioService } from '../services/questionario.service';
import { CommonModule } from '@angular/common';
import { QuestaoComponent } from './questao.component';
import { RespostaService } from '../services/resposta.service';

@Component({
  selector: 'app-questionario',
  standalone: true,
  imports: [CommonModule, QuestaoComponent],
  template: `
    @if (questionario) {
      <h2>{{ questionario.descricao }}</h2>

      @for (questao of questionario.listaQuestao; track questao.idQuestao) {
        <app-questao [questao]="questao" [respostaQuestao]="getRespostaParaQuestao(questao.idQuestao)"></app-questao>
      }
    }
  `,
  styles: [`
    div {
      margin-bottom: 8px;
    }
  `]
})
export class QuestionarioComponent implements OnInit {
  questionario: QuestionarioDTO | undefined;

  private route = inject(ActivatedRoute);
  private questionarioService = inject(QuestionarioService);
  private respostaService = inject(RespostaService);

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.questionarioService.getById(+id).subscribe(data => {
        this.questionario = data;
        this.respostaService.getRespostasByQuestionarioId(+id).subscribe(respostas => {
          if (this.questionario) {
            this.questionario.respostaQuestionario = respostas;
          }
        });
      });
    }
  }

  getRespostaParaQuestao(idQuestao: number | undefined): RespostaQuestaoDTO | undefined {
    if (!idQuestao || !this.questionario?.respostaQuestionario?.listaRespostaQuestao) {
      return undefined;
    }
    return this.questionario.respostaQuestionario.listaRespostaQuestao.find(
      resposta => resposta.idQuestao === idQuestao
    );
  }
}
