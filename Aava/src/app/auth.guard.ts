import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from './auth.service';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  if (authService.isAuthenticated) {
    return true;
  } else {
    // Tenta iniciar o fluxo de login OIDC
    authService.login();
    // Retorna false para cancelar a navegação atual
    return false;
  }
};
