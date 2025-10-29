import { ApplicationConfig, provideZoneChangeDetection, APP_INITIALIZER } from '@angular/core';
import { provideRouter } from '@angular/router';
import { HTTP_INTERCEPTORS, provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

import { routes } from './app.routes';
import { AuthService } from './auth.service';
import { DefaultOAuthInterceptor, OAuthStorage, provideOAuthClient } from 'angular-oauth2-oidc';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),

    // Configura o HttpClient para usar interceptors baseados em DI
    provideHttpClient(withInterceptorsFromDi()),

    provideOAuthClient(), // Fornece o OAuthService

    // Define o storage que a biblioteca OIDC usará
    { provide: OAuthStorage, useFactory: () => localStorage },

    {
      provide: APP_INITIALIZER,
      useFactory: (authService: AuthService) => () => authService.runInitialLoginSequence(),
      deps: [AuthService],
      multi: true,
    },

    // Adiciona o interceptor da biblioteca OIDC
    {
      provide: HTTP_INTERCEPTORS,
      useClass: DefaultOAuthInterceptor,
      multi: true
    }
  ]
};
