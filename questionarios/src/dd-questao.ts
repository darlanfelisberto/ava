import {LitElement, css, html} from 'lit'
import {customElement, property} from "lit/decorators.js";
import type {Questao, RespostaQuestaoDTO, RespostaQuestionarioDTO} from "./model.ts";


@customElement('dd-questao')
export class DDQuestao extends LitElement {

    @property({ type: Object })
    questao?: Questao;

    @property({ type: Object })
    respostaQuestao?: RespostaQuestaoDTO;

    constructor() {
        super();
    }



    render() {
        return html`
            <div class="card">
                questao-${this.questao?.descricao}
                res--${this.respostaQuestao?.idQuestao}
            </div>
            <div>
                <dd-alternativa .questao="${this.questao}" .respostaAlternativa="${this.respostaQuestao?.listaRespostaAlternativa}"></dd-alternativa>
            </div>
        `
    }

    static styles = css`
    div {
      background-color: coral;
    }
  `;
}
