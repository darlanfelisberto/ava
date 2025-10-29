import "./my-element.ts";
import "./dd-questionario.ts";
import "./dd-questao.ts"


export interface Questionario{
    idQuestionario?: number;
    descricao?: string;
    listaQuestao?: Questao[];
    respostaQuestionario?:RespostaQuestionarioDTO;
}

export interface Questao{
    idQuestao?: number;
    tipoQuestao?: TipoQuestao;
    descricao?: string;
    resposta: string[];
    listaAlternativa?: Alternativa[];
}

export interface Alternativa{
    idAlternativa?: number;
    descricao?: string;
}

export interface TipoQuestao{
    idTipoQuestao?: number;
    nome?: string;
}

export interface RespostaQuestaoDTO {
    idQuestao?: number;
    idRespostaQuestao?: number;
    listaRespostaAlternativa?: RespostaAlternativaDTO[];
}

export interface RespostaAlternativaDTO {
    idRespostaAlternativa?: number;
    idRespostaQuestao?: number;
    IdAlternativa?: number;
}

export interface RespostaQuestionarioDTO {
    idQuestionario?: number;
    idRespostaQuestionario?: number;
    listaRespostaQuestao?: RespostaQuestaoDTO[];
}
