export interface QuestionarioDTO{
    idQuestionario?: number;
    nome?: string;
    descricao?: string;
    paginas?: PaginaDTO[];
    listaQuestao?: QuestaoDTO[];
    respostaQuestionario?:RespostaQuestionarioDTO;
}

export interface PaginaDTO {
    idPagina?: number;
    nome?: string;
    descricao?: string;
    questoes?: QuestaoDTO[];
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

export enum TipoQuestao{
  desc = 1,
  unic = 2,
  mult = 3
}

export interface RespostaQuestaoDTO {
    idQuestao?: number;
    idRespostaQuestao?: number;
    listaAlternativa?: AlternativaDTO[];
}

export interface RespostaQuestionarioDTO {
    idQuestionario?: number;
    idRespostaQuestionario?: number;
    listaRespostaQuestao?: RespostaQuestaoDTO[];
}
