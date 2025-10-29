import {LitElement, css, html} from 'lit'
import {customElement, property} from "lit/decorators.js";
import {type Questao, type RespostaAlternativaDTO} from "./model.ts";


@customElement('dd-alternativa')
export class DDAlternativa extends LitElement {

    @property({ type: Object })
    questao?: Questao;

    listaRespostaAlternativa?:RespostaAlternativaDTO[];

    constructor() {
        super();
    }

    render() {
        return html`${this.questao?.listaAlternativa?.map(item => html`<input type="radio" value="${item.idAlternativa}" name="q-${this.questao?.idQuestao}" @click="${this.change}"/> ${item.descricao}`)}`
    }

    public change(e: Event){
        console.log(this.questao)
        console.log((e.target as HTMLInputElement).value)
        this.questao?.resposta?.push(((e.target as HTMLInputElement).value))
    }

    static styles = css`
    div {
      background-color: coral;
    }
  `;
}
