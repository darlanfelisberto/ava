import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { QuestionarioDTO } from '../model';
import {Router, RouterLink} from '@angular/router';
import { QuestionarioService } from '../services/questionario.service';
import { CommonModule } from '@angular/common';
import {TableModule} from 'primeng/table';
import {Observable, Subject, takeUntil} from 'rxjs';
import {Button} from 'primeng/button';
import {Tooltip} from 'primeng/tooltip';
import {DialogModule} from 'primeng/dialog';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {InputTextModule} from 'primeng/inputtext';
import {ValidacaoInputComponent} from '../componentes/validacao-input.component';
import {ConfirmationService, MessageService} from 'primeng/api';
import {ConfirmPopup} from 'primeng/confirmpopup';

@Component({
  selector: 'app-meus-questionarios',
  standalone: true,
  imports: [RouterLink, CommonModule, TableModule, Button, Tooltip, DialogModule, ReactiveFormsModule, InputTextModule, ValidacaoInputComponent, ConfirmPopup],
  providers: [ConfirmationService, MessageService],
  template: `
    <style>
      .p-fluid .p-field { margin-bottom: 1rem; }
    </style>

    <h2>Minhas provas/questionários</h2>

    <p-table [value]="(this.questionarios$  | async) ?? []">
      <ng-template pTemplate="header">
        <tr>
          <th>ID</th>
          <th>Nome</th>
          <th>Descrição</th>
        </tr>
      </ng-template>
      <ng-template #body let-questionario>
        <tr>
          <td>{{ questionario.idQuestionario }}</td>
          <td>{{ questionario.nome }}</td>
          <td>{{ questionario.descricao }}</td>
          <td>
            <p-button icon="pi pi-pencil" [rounded]="true" [text]="true" routerLink="/questionario/{{questionario.idQuestionario}}" />
          </td>
        </tr>
      </ng-template>
    </p-table>

    <div class="grid grid-cols-1 md:grid-cols-3 ">
      <div class=" ">01 01 01 01 01 01 01 01 01 01 </div>
      <div class=" ">02 02 02 02 02 02 02 02 02 02 02 </div>
      <div class="">03 03 03 03 03 03 03 03 03 03 03 </div>
      <div class=" ">04 04 04 04 04 04 04 04 04 04 04 04 </div>

    </div>

    <div class="botoes-form">
      <p-button (click)="abrirDialogNovo()" icon="pi pi-plus" pTooltip="Novo Questionario" tooltipPosition="left" type="button"></p-button>
    </div>

    <p-dialog header="Novo Questionário" [(visible)]="exibirDialog" [modal]="true" [style]="{width: '50vw'}" (onHide)="fecharDialog()">

      <form [formGroup]="form"  >
        <div class="grid grid-cols-1 md:grid-cols-1 gap-4">
          <div class="flex flex-col">
            <label class="w-full">Nome: </label>
            <input type="text" formControlName="nome"  pInputText class="required-input"/>
            <app-validacao-input [control]="form.get('nome')" nomeDoCampo="Nome"></app-validacao-input>
          </div>

          <div class="flex flex-col">
            <label>Descrição:</label>
            <textarea
              placeholder="Descrição do Questionário"
              formControlName="descricao"
              rows="1"
            ></textarea>
          </div>
        </div>
        <div class="p-dialog-footer" style="margin-top: 1rem; text-align: right">
          <p-button label="Cancelar" icon="pi pi-times" styleClass="p-button-text" (click)="fecharDialog()"></p-button>
          <p-button label="Salvar" icon="pi pi-check" (click)="confirmarSalvar($event)"  [disabled]="form.invalid"></p-button>
        </div>
      </form>
    </p-dialog>
    <p-confirmpopup />
  `,
  styles: [`
    /* Estilos existentes */
    .botoes-form {
      position: fixed;
      bottom: 2rem;
      right: 2rem;
    }

    ul {
      list-style-type: none;
      padding: 0;
    }
    li {
      margin: 8px 0;
    }
    a {
      text-decoration: none;
      color: #333;
    }
    a:hover {
      color: #007bff;
    }
  `]
})
export class MeusQuestionariosComponent implements OnInit, OnDestroy {
  questionarios$?: Observable<QuestionarioDTO[]>;
  exibirDialog = false;
  form: FormGroup;

  private questionarioService = inject(QuestionarioService);
  private confirmationService: ConfirmationService = inject(ConfirmationService);
  private fb = inject(FormBuilder);
  router: Router = inject(Router);

  constructor() {
    this.form = this.fb.group({
      idQuestionario: [null],
      nome: ['', [Validators.required, Validators.minLength(3)]],
      descricao: ['']
    });
  }

  ngOnInit(): void {
    this.carregarQuestionarios();
  }

  ngOnDestroy(): void {
  }

  carregarQuestionarios(): void {
    this.questionarios$ = this.questionarioService.getAll();
  }

  abrirDialogNovo(): void {
    this.form.reset();
    this.exibirDialog = true;
  }

  fecharDialog(): void {
    this.exibirDialog = false;
  }

  confirmarSalvar( event: Event){
    console.log(event);
    this.confirmationService.confirm({
      target: event.currentTarget as EventTarget,
      message: 'Você tem certeza?',
      icon: 'pi pi-question',
      rejectButtonProps: {
        label: 'Cancel',
        severity: 'secondary',
        outlined: true
      },
      acceptButtonProps: {
        label: 'Save'
      },
      accept: () => {
        this.salvarQuestionario()
        console.log('Confirmed');
        // this.messageService.add({ severity: 'info', summary: 'Confirmed', detail: 'You have accepted', life: 3000 });
      },
      reject: () => {
        console.log('Rejected');
        // this.messageService.add({ severity: 'error', summary: 'Rejected', detail: 'You have rejected', life: 3000 });
      }
    });
  }

  salvarQuestionario(): void {
    if (this.form.invalid) {
      return;
    }

    this.questionarioService.create(this.form.value)
    .subscribe({
      next: (response) => {
        console.log(response)
        if (response.idQuestionario) {

          this.router.navigate(['/questionario/editar/', response.idQuestionario]);
        } else {
          this.carregarQuestionarios(); // Recarrega a lista
          console.error('ID do questionário não retornado na resposta.');
        }
        this.fecharDialog();
      },
      error: (err) => {
        console.error('Erro ao salvar o questionário:', err);
        // TODO: Implementar um serviço de mensagens para exibir o erro ao usuário.
      }
    });
  }
}
