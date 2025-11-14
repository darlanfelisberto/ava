import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { QuestionarioDTO } from '../model';
import { GenericService } from './generic.service';

@Injectable({
  providedIn: 'root'
})
export class QuestionarioService extends GenericService<QuestionarioDTO, string> {
  constructor(http: HttpClient) {
    super(http, 'http://localhost:8080/ava/api/questionario');
  }
}
