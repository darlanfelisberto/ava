
import { Component, Input, Output, EventEmitter, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormGroup, FormArray, FormBuilder, Validators } from '@angular/forms';
import { QuestaoDTO, TipoQuestao, AlternativaDTO } from '../model';
import { Select } from 'primeng/select';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { AlternativaDescComponent } from './alternativa-desc.component';
import { AlternativaEscolhaComponent } from './alternativa-escolha.component';
import { ValidacaoInputComponent } from '../componentes/validacao-input.component';

@Component({
  selector: 'app-cadastro-questao',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    InputTextModule,
    ButtonModule,
    Select,
    AlternativaDescComponent,
    AlternativaEscolhaComponent,
    ValidacaoInputComponent
  ],
  template: `
    <div class="card" [formGroup]="questaoFormGroup">
      <div class="card-header">
        <p-button icon="pi pi-trash" styleClass="p-button-danger p-button-text" title="Excluir"
                  (click)="removerQuestao.emit()" type="button"></p-button>
      </div>
      <div class="card-main flex">
        <div class="main-index flex">
          <span class="question-index">{{ totalPreviousQuestions + questionIndex + 1 }}.</span>
        </div>
        <div class="w-full">
          <input
            type="text"
            class="title-input "
            placeholder="Enunciado da Questão"
            formControlName="descricao"
          />
          <app-validacao-input [control]="questaoFormGroup.get('descricao')"
                               nomeDoCampo="Enunciado da Questão"></app-validacao-input>
          <div formArrayName="listaAlternativa">
            @switch (questaoFormGroup.get('tipoQuestao')?.value) {
              @case (TipoQuestao.desc) {
                <app-alternativa-desc></app-alternativa-desc>
              }
              @case (TipoQuestao.unic) {
                <app-alternativa-escolha [alternativasArray]="alternativas"
                                         [tipo]="TipoQuestao.unic"></app-alternativa-escolha>
              }
              @case (TipoQuestao.mult) {
                <app-alternativa-escolha [alternativasArray]="alternativas"
                                         [tipo]="TipoQuestao.mult"></app-alternativa-escolha>
              }
            }
          </div>
        </div>

      </div>

      <div class="card-footer flex justify-items-star">
        <p-select
          [options]="tiposQuestao"
          formControlName="tipoQuestao"
          optionLabel="label"
          optionValue="value"
          placeholder="Tipo da Questão"
          (onChange)="onTipoQuestaoChange()"
        ></p-select>
        <app-validacao-input [control]="questaoFormGroup.get('tipoQuestao')"
                             nomeDoCampo="Tipo da Questão"></app-validacao-input>
      </div>
    </div>
  `,
  styles: [`
    .card {
      border: 1px solid #eee;
      padding: 1rem;
      margin-top: 1rem;
      border-radius: 5px;
      background-color: #f9f9f9;
    }
    .card-header {
      display: flex;
      justify-content: flex-end;
    }
    .card .card-header p-button, .card .card-footer {
      visibility: hidden;
    }
    .card:hover .card-header p-button, .card:hover .card-footer {
      visibility: visible;
    }
    .question-index {
      font-size: 1rem;
      font-weight: 400;
      color: #212529;
      margin-right: 0.5rem;
    }
  `]
})
export class CadastroQuestaoComponent {
  @Input() questaoFormGroup!: FormGroup;
  @Input() pageIndex!: number;
  @Input() questionIndex!: number;
  @Input() totalPreviousQuestions!: number;
  @Output() removerQuestao = new EventEmitter<void>();

  private fb = inject(FormBuilder);

  tiposQuestao = [
    { label: 'Descritiva', value: TipoQuestao.desc },
    { label: 'Única Escolha', value: TipoQuestao.unic },
    { label: 'Múltipla Escolha', value: TipoQuestao.mult }
  ];

  TipoQuestao = TipoQuestao;

  get alternativas() {
    return this.questaoFormGroup.get('listaAlternativa') as FormArray;
  }

  onTipoQuestaoChange() {
    this.alternativas.clear();
    const tipo = this.questaoFormGroup.get('tipoQuestao')?.value;
    if (tipo === TipoQuestao.unic || tipo === TipoQuestao.mult) {
      this.alternativas.push(this.fb.group({ descricao: ['', Validators.required] }));
    }
  }
}
