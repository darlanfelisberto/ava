import {inject, Injectable} from '@angular/core';
import {GenericService} from './generic.service';
import {QuestionarioDTO} from '../model';
import {HttpClient} from '@angular/common/http';
import {MessageService} from 'primeng/api';

@Injectable({
  providedIn: 'root'
})
export class AppMessagesService {

  public messageService: MessageService = inject(MessageService)

  constructor() {
  }

  public success(message: string) {
    this.messageService.add({ severity: 'success', summary: 'Sucesso', detail: message });
  }

  public error(message: string) {
    this.messageService.add({ severity: 'error', summary: 'Erro', detail: message });
  }
}
