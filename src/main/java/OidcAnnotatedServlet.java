import jakarta.annotation.security.DeclareRoles;
import jakarta.security.enterprise.authentication.mechanism.http.OpenIdAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.openid.ClaimsDefinition;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

//@WebServlet("/OidcAnnotatedServlet")
//@OpenIdAuthenticationMechanismDefinition(
//        providerURI = "http://localhost:9080/erp/auth/realms/feliva",
//        clientId = "erp",
//        clientSecret = "3fd70ff4-fe2b-47b6-a8a3-cd1cf281a937",
//        useSession = false,
//        scope = "openid",
//        redirectToOriginalResource = true
//)
//@DeclareRoles({"admin"})
//public class OidcAnnotatedServlet extends HttpServlet {
//}