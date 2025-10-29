export interface QuestionarioDTO{
    idQuestionario?: number;
    descricao?: string;
    listaQuestao?: QuestaoDTO[];
    respostaQuestionario?:RespostaQuestionarioDTO;
}

export interface QuestaoDTO{
    idQuestao?: number;
    tipoQuestao?: TipoQuestao;
    descricao?: string;
    resposta: string[];
    listaAlternativa?: AlternativaDTO[];
}

export interface AlternativaDTO{
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
