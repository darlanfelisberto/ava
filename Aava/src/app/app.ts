import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  template: `

    <div class="ds-template">
      <div class="logo">logo</div>
      <div class="header"><h2>My Header</h2></div>
      <div class="menu">

      </div>
      <div class="content">
        <router-outlet />
      </div>
    </div>
  `,
  styles: `
    nav{
      background-color: #373737;
      width: 200px;
    }
  `
})
export class App {
  protected readonly title = signal('Aava');
}
