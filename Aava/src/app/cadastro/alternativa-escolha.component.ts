import { Component, Input, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormArray, FormBuilder, Validators, FormGroup } from '@angular/forms';
import { QuestaoDTO, AlternativaDTO, TipoQuestao } from '../model';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { ValidacaoInputComponent } from '../componentes/validacao-input.component';

@Component({
  selector: 'app-alternativa-escolha',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, ButtonModule, InputTextModule, ValidacaoInputComponent],
  template: `
    @for (alternativa of alternativaControls; track alternativa; let i = $index) {
      <div class="alternativa" [formGroup]="alternativa">
        @if (tipo === TipoQuestao.unic) {
          <input type="radio" [name]="'alternativa_' + i" [disabled]="true" />
        } @else {
          <input type="checkbox" [disabled]="true" />
        }
        <input pInputText type="text" formControlName="descricao" placeholder="Alternativa" class="title-input" />
        <p-button icon="pi pi-trash" (click)="removerAlternativa(i)" styleClass="p-button-danger p-button-text" type="button"></p-button>
        <app-validacao-input [control]="alternativa.get('descricao')" nomeDoCampo="Alternativa"></app-validacao-input>
      </div>
    }
    <p-button icon="pi pi-plus" (click)="adicionarAlternativa()" styleClass="p-button-text add-button" type="button"></p-button>
  `,
  styles: [`
    .alternativa {
      display: flex;
      align-items: center;
      margin-top: 0.5rem;
    }
    .alternativa input[type="radio"], .alternativa input[type="checkbox"] {
      margin-right: 0.5rem;
    }
    .alternativa p-button {
      visibility: hidden;
    }
    .alternativa:hover p-button {
      visibility: visible;
    }
    .add-button {
      visibility: hidden;
    }
    :host-context(.card:hover) .add-button {
      visibility: visible;
    }
    .title-input {
        width: 100%;
        border: none;
        background-color: transparent;
        outline: none;
        padding: 10px 0;
        border-bottom: 2px solid transparent;
        transition: border-bottom-color 0.3s;
        box-shadow: none;
        font-size: 1rem;
        font-weight: 400;
        color: #212529;
      }
      .title-input:hover, .title-input:focus {
        border-bottom-color: #dee2e6;
      }
      ::placeholder {
        color: #adb5bd;
        opacity: 1; /* Firefox */
      }

      ::-ms-input-placeholder { /* Edge */
        color: #adb5bd;
      }
  `]
})
export class AlternativaEscolhaComponent {
  @Input() alternativasArray!: FormArray;
  @Input() tipo!: TipoQuestao.unic | TipoQuestao.mult;

  private fb = inject(FormBuilder);

  TipoQuestao = TipoQuestao;

  get alternativaControls() {
    return this.alternativasArray.controls as FormGroup[];
  }

  adicionarAlternativa() {
    const alternativaForm = this.fb.group({
      descricao: ['', Validators.required]
    });
    this.alternativasArray.push(alternativaForm);
    alternativaForm.markAllAsTouched();
  }

  removerAlternativa(index: number) {
    this.alternativasArray.removeAt(index);
  }
}
