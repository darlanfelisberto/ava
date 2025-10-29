import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AlternativaDTO, RespostaQuestaoDTO } from '../model';

@Component({
  selector: 'app-alternativa',
  standalone: true,
  imports: [CommonModule],
  template: `
    @for (alternativa of listaAlternativa; track alternativa.idAlternativa) {
      <div>
        <input
          type="radio"
          [name]="idQuestao?.toString()"
          [value]="alternativa.idAlternativa"
          [checked]="isSelecionada(alternativa.idAlternativa)"
          (change)="onAlternativaChange($event, alternativa.idAlternativa)">
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
  @Input() idQuestao: number | undefined;
  @Input() respostaQuestao: RespostaQuestaoDTO | undefined;

  @Output() respostaChange = new EventEmitter<{idAlternativa?: number, texto?: string, selecionada: boolean}>();

  onAlternativaChange(event: Event, idAlternativa: number | undefined) {
    const input = event.target as HTMLInputElement;
    this.respostaChange.emit({ idAlternativa: idAlternativa, selecionada: input.checked });
  }

  onDissertativaChange(event: Event) {
    const textarea = event.target as HTMLTextAreaElement;
    this.respostaChange.emit({ texto: textarea.value, selecionada: true });
  }

  isSelecionada(idAlternativa: number | undefined): boolean {
    if (!idAlternativa || !this.respostaQuestao?.listaRespostaAlternativa) {
      return false;
    }
    return this.respostaQuestao.listaRespostaAlternativa.some(resp => resp.IdAlternativa === idAlternativa);
  }

  getRespostaDissertativa(): string {
    // O model atual não suporta salvar texto dissertativo, então retornamos string vazia.
    // O ideal seria ajustar o RespostaQuestaoDTO para ter um campo para isso.
    return '';
  }
}
