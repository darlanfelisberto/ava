import {LitElement, css, html} from 'lit'
import {customElement, property} from 'lit/decorators.js'
import type {Questao, Questionario, RespostaQuestaoDTO} from "./model.ts";


@customElement('dd-questionario')
export class DDQuestionario extends LitElement {

    private count = 0;
    private questionario?: Questionario = {};

    constructor() {
        super();
        this.getData();
    }


    async getData() {
        console.log("função")
        let id = 1;
        const urlQuestionario = "http://localhost:8080/ava/api/questionario/findById/"+id;
        const urlRespostaQuestionario = "http://localhost:8080/ava/api/questionario/minhaRespostaByQuestionario/"+id;
        try {
            const responseQ = await fetch(urlQuestionario);
            const responseR = await fetch(urlRespostaQuestionario);
            const json = await responseQ.json();
            this.questionario = json;
            if(this.questionario) {
                this.questionario.respostaQuestionario = await responseR.json();
            }

            console.log(this.questionario)
            console.log(json)
            this.requestUpdate();
        } catch (e) {
            console.log(e)
        }
    }

    buscaResposta(questao:Questao):RespostaQuestaoDTO {

        for(let item of this.questionario?.respostaQuestionario?.listaRespostaQuestao){
            if (item.idQuestao === questao.idQuestao) {
                return item;
            }
        }
        return {};
    }

    render() {
        if(!this.questionario){
            return html``;
        }
        return html`
            <div>
                <span>${this.questionario?.descricao}</span>
                <span>${this.questionario?.idQuestionario}</span>
            </div>
            
            <div class="card">
                ${this.questionario?.listaQuestao?.map(questao => html`<dd-questao .questao="${questao}" .respostaQuestao="${this.buscaResposta(questao)}"></dd-questao>`)}
            </div>
        `
    }

    public requestUpdate() {
        this.count++
        super.requestUpdate()
    }

    private _onClick() {
        this.count++
    }
}
declare global {
    interface HTMLElementTagNameMap {
        'dd-questionario': DDQuestionario
    }
}