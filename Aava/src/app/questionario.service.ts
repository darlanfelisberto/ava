import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { QuestionarioDTO } from './model'; // Assumindo que QuestionarioDTO é compatível com Questionario

@Injectable({
  providedIn: 'root'
})
export class QuestionarioService {
  private apiUrl = 'http://localhost:8080/ava/api/questionario/listAllQuestionarios';

  constructor(private http: HttpClient) { }

  getAllQuestionarios(): Observable<QuestionarioDTO[]> {
    return this.http.get<QuestionarioDTO[]>(this.apiUrl);
  }
}
