
import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AbstractControl } from '@angular/forms';

@Component({
  selector: 'app-validacao-input',
  standalone: true,
  imports: [CommonModule],
  template: `
    @if (control && control.invalid && (control.dirty || control.touched)) {
      <div class="error-message">
        @if (control.errors?.['required']) {
          <div>{{ nomeDoCampo }} é obrigatório.</div>
        }
      </div>
    }
  `,
  styles: [`
    .error-message {
      color: red;
      font-size: 0.875em;
      margin-top: 5px;
    }
  `]
})
export class ValidacaoInputComponent {
  @Input() control: AbstractControl | null = null;
  @Input() nomeDoCampo: string = 'Este campo';
}
