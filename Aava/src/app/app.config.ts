import { ApplicationConfig, provideZoneChangeDetection, APP_INITIALIZER } from '@angular/core';
import { provideRouter } from '@angular/router';
import { HTTP_INTERCEPTORS, provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';

import { routes } from './app.routes';
import { AuthService } from './auth.service';
import { DefaultOAuthInterceptor, OAuthStorage, provideOAuthClient } from 'angular-oauth2-oidc';
import {providePrimeNG} from 'primeng/config';
import Aura from '@primeuix/themes/aura';
import {MessageService} from 'primeng/api';
import {AppMessagesService} from './services/appMessages.service';

export const appConfig: ApplicationConfig = {
  providers: [
    // provideZoneChangeDetection({ eventCoalescing: true }),
    AppMessagesService,
    MessageService,
    provideRouter(routes),
    provideAnimationsAsync(),
    providePrimeNG({
      theme: {
        preset: Aura
      },
      translation: {
        accept: 'Sim',
        reject: 'Não',
        //translations
      }
    }),

    // Configura o HttpClient para usar interceptors baseados em DI
    provideHttpClient(),

    provideOAuthClient(), // Fornece o OAuthService
    // Define o storage que a biblioteca OIDC usará
    { provide: OAuthStorage, useFactory: () => localStorage },

  ]
};
