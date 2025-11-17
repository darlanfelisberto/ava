import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { QuestionarioDTO } from '../model';
import { RouterLink } from '@angular/router';
import { QuestionarioService } from '../services/questionario.service';
import { CommonModule } from '@angular/common';
import {TableModule} from 'primeng/table';
import {Observable, Subject, takeUntil} from 'rxjs';
import {Button} from 'primeng/button';
import {Tooltip} from 'primeng/tooltip';
import {DialogModule} from 'primeng/dialog';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {InputTextModule} from 'primeng/inputtext';

@Component({
  selector: 'app-meus-questionarios',
  standalone: true,
  imports: [RouterLink, CommonModule, TableModule, Button, Tooltip, DialogModule, ReactiveFormsModule, InputTextModule],
  template: `
    <style>
      .p-fluid .p-field { margin-bottom: 1rem; }
    </style>

    <h2>Minhas provas/questionários</h2>

    <p-table [value]="(this.questionarios$  | async) ?? []">
      <ng-template pTemplate="header">
        <tr>
          <th>ID</th>
          <th>Descrição</th>
          <th></th>
        </tr>
      </ng-template>
      <ng-template #body let-questionario>
        <tr>
          <td>{{ questionario.idQuestionario }}</td>
          <td>{{ questionario.descricao }}</td>
          <td>
            <p-button icon="pi pi-pencil" [rounded]="true" [text]="true" routerLink="/questionario/{{questionario.idQuestionario}}" />
          </td>
        </tr>
      </ng-template>
    </p-table>


    <div class="botoes-form">
      <p-button (click)="abrirDialogNovo()" icon="pi pi-plus" pTooltip="Novo Questionario" tooltipPosition="left" type="button"></p-button>
    </div>

    <p-dialog header="Novo Questionário" [(visible)]="exibirDialog" [modal]="true" [style]="{width: '50vw'}"
              (onHide)="fecharDialog()">
      <form [formGroup]="form" (ngSubmit)="salvarQuestionario()" class="p-fluid">
        <div class="p-field">
          <label for="descricao">Descrição</label>
          <input id="descricao" type="text" pInputText formControlName="descricao"/>
          <small *ngIf="form.get('descricao')?.invalid && form.get('descricao')?.touched" class="p-error">
            Descrição é obrigatória.
          </small>
        </div>

        <div class="p-dialog-footer" style="margin-top: 1rem; text-align: right">
          <p-button label="Cancelar" icon="pi pi-times" styleClass="p-button-text" (click)="fecharDialog()"></p-button>
          <p-button label="Salvar" icon="pi pi-check" type="submit" [disabled]="form.invalid"></p-button>
        </div>
      </form>
    </p-dialog>

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
  private fb = inject(FormBuilder);
  private destroy$ = new Subject<void>();

  constructor() {
    this.form = this.fb.group({
      idQuestionario: [null],
      descricao: ['', [Validators.required, Validators.minLength(3)]]
    });
  }

  ngOnInit(): void {
    this.carregarQuestionarios();
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
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

  salvarQuestionario(): void {
    if (this.form.invalid) {
      return;
    }

    this.questionarioService.create(this.form.value)
      .pipe(takeUntil(this.destroy$))
      .subscribe(() => {
        this.carregarQuestionarios(); // Recarrega a lista
        this.fecharDialog();
      });
  }
}
