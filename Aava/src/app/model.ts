export interface QuestionarioDTO{
    idQuestionario?: string;
    nome?: string;
    descricao?: string;
    paginas?: PaginaDTO[];
    listaQuestao?: QuestaoDTO[];
    respostaQuestionario?:RespostaQuestionarioDTO;
}

export interface PaginaDTO {
    idPagina?: string;
    nome?: string;
    descricao?: string;
    questoes?: QuestaoDTO[];
}

export interface QuestaoDTO{
    idQuestao?: string;
    tipoQuestao?: TipoQuestao;
    descricao?: string;
    resposta: string[];
    listaAlternativa?: AlternativaDTO[];
}

export interface AlternativaDTO{
    idAlternativa?: string;
    descricao?: string;
}

export enum TipoQuestao{
  desc,
  unic,
  mult
}

export interface RespostaQuestaoDTO {
    idQuestao?: string;
    idRespostaQuestao?: string;
    listaAlternativa?: AlternativaDTO[];
}

export interface RespostaQuestionarioDTO {
    idQuestionario?: string;
    idRespostaQuestionario?: string;
    listaRespostaQuestao?: RespostaQuestaoDTO[];
}
