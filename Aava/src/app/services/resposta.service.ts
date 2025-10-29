import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RespostaQuestionarioDTO } from '../model';
import { GenericService } from './generic.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RespostaService extends GenericService<RespostaQuestionarioDTO, number> {
  constructor(http: HttpClient) {
    super(http, 'http://localhost:8080/ava/api/resposta');
  }

  getRespostasByQuestionarioId(idQuestionario: number): Observable<RespostaQuestionarioDTO> {
    return this.http.get<RespostaQuestionarioDTO>(`${this.apiUrl}/questionario/${idQuestionario}`);
  }
}
