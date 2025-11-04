import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuestionarioDTO, RespostaQuestaoDTO, RespostaQuestionarioDTO } from '../model';
import { QuestionarioService } from '../services/questionario.service';
import { CommonModule } from '@angular/common';
import { QuestaoComponent } from './questao.component';
import { RespostaService } from '../services/resposta.service';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-questionario',
  standalone: true,
  imports: [CommonModule, QuestaoComponent],
  template: `
    @if (questionario) {
      <h2>{{ questionario.descricao }}</h2>

      @for (questao of questionario.listaQuestao; track questao.idQuestao) {
        <app-questao
          [questao]="questao"
          [respostaQuestao]="getRespostaParaQuestao(questao.idQuestao)"
          (respostaQuestaoChange)="onRespostaQuestaoChange($event)">
        </app-questao>
      }
      <button (click)="submitAnswers()">Enviar Respostas</button>
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
      forkJoin({
        questionario: this.questionarioService.getById(+id),
        respostas: this.respostaService.getRespostasByQuestionarioId(+id)
      }).subscribe(({ questionario, respostas }) => {
        this.questionario = questionario;
        if (this.questionario) {
          this.questionario.respostaQuestionario = respostas;
        }
        // console.log('Dados carregados com forkJoin:', this.questionario);
      });
    }
  }

  getRespostaParaQuestao(idQuestao: number | undefined): RespostaQuestaoDTO | undefined {
    console.log('idQuestao:', idQuestao);
    if (!idQuestao || !this.questionario?.respostaQuestionario?.listaRespostaQuestao) {
      return undefined;
    }
    return this.questionario.respostaQuestionario.listaRespostaQuestao.find(
      resposta => resposta.idQuestao === idQuestao
    );
  }

  onRespostaQuestaoChange(novaRespostaQuestao: RespostaQuestaoDTO) {
    if (!this.questionario) {
      return;
    }

    if (!this.questionario.respostaQuestionario) {
      this.questionario.respostaQuestionario = { idQuestionario: this.questionario.idQuestionario, listaRespostaQuestao: [] };
    } else if (!this.questionario.respostaQuestionario.listaRespostaQuestao) {
      this.questionario.respostaQuestionario.listaRespostaQuestao = [];
    }

    const listaRespostas = this.questionario.respostaQuestionario.listaRespostaQuestao;
    const index = listaRespostas!.findIndex(r => r.idQuestao === novaRespostaQuestao.idQuestao);

    if (index > -1) {
      // Atualiza a resposta existente
      listaRespostas![index] = novaRespostaQuestao;
    } else {
      // Adiciona nova resposta
      listaRespostas!.push(novaRespostaQuestao);
    }

    console.log('Resposta atualizada no QuestionarioComponent:', this.questionario.respostaQuestionario);
  }

  submitAnswers(): void {
    if (this.questionario?.respostaQuestionario) {
      this.respostaService.salvarRespostas(this.questionario.respostaQuestionario).subscribe({
        next: (response) => {
          console.log('Respostas enviadas com sucesso!', response);
          // Optionally, handle success (e.g., show a message, navigate)
        },
        error: (error) => {
          console.error('Erro ao enviar respostas:', error);
          // Optionally, handle error (e.g., show an error message)
        }
      });
    } else {
      console.warn('Nenhuma resposta para enviar.');
    }
  }
}
