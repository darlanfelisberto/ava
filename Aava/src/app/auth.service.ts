import { Injectable } from '@angular/core';
import { AuthConfig, OAuthService } from 'angular-oauth2-oidc';

const authCodeFlowConfig: AuthConfig = {
  // URL do provedor de identidade
  issuer: 'http://localhost:8080/ava/auth/realms/auth',

  // URL da sua aplicação Angular, para onde o usuário será redirecionado após o login
  redirectUri: window.location.origin,

  // O ID do cliente da sua aplicação, registrado no provedor de identidade
  clientId: 'ava', // <-- SUBSTITUA PELO SEU CLIENT ID

  // Escopos que sua aplicação está solicitando
  scope: 'openid',

  // Usa o fluxo de "Authorization Code" com PKCE, que é o mais seguro para SPAs
  responseType: 'code',

  dummyClientSecret: '3fd70ff4-fe2b-47b6-a8a3-cd1cf281a937',

  // Pede para o provedor desabilitar a página de consentimento se o usuário já consentiu antes
  showDebugInformation: true, // Defina como false em produção
};

@Injectable({ providedIn: 'root' })
export class AuthService {

  constructor(private oauthService: OAuthService) {}

  public runInitialLoginSequence(): Promise<void> {
    this.oauthService.configure(authCodeFlowConfig);

    return this.oauthService.loadDiscoveryDocumentAndTryLogin()
      .then(() => {
        if (this.oauthService.hasValidIdToken()) {
          // Se já estiver logado, pode buscar informações do usuário, etc.
        }
      });
  }

  public login(): void {
    this.oauthService.initLoginFlow();
  }

  public logout(): void {
    this.oauthService.logOut();
  }

  public get isAuthenticated(): boolean {
    return this.oauthService.hasValidIdToken() && this.oauthService.hasValidAccessToken();
  }
}
