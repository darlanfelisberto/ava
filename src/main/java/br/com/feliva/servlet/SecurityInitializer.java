package br.com.feliva.servlet;

//import com.ibm.wsspi.webcontainer.servlet.IServletConfig;
import jakarta.servlet.*;
import jakarta.servlet.annotation.ServletSecurity;

import java.util.Set;

// ServletContainerInitializer to add security constraint
//@HandlesTypes(SecuredServlet.class) // Optional: Specify types to scan for
public class SecurityInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> classes, ServletContext ctx) throws ServletException {
        // Get the registration for the servlet to be secured
        ServletRegistration.Dynamic securedServletRegistration = (ServletRegistration.Dynamic) ctx.getServletRegistration("Faces Servlet");
//
        System.out.println("Programmatically adsdded security coansgtraint tso secure sdServlset.");
        if (securedServletRegistration != null) {


//com.ibm.ws.webcontainer.servlet.ServletConfig
            try {
//                ServletConfig s = (ServletConfig)securedServletRegistration;
//                s.getServletSecurity().getMethodNames().forEach(s1 -> {
//                    System.out.println(s1);
//                });
            }catch (Exception e){
                e.printStackTrace();
            }

//            // Create HttpConstraintElement for the desired security settings
           HttpConstraintElement httpConstraintElement = new HttpConstraintElement(ServletSecurity.TransportGuarantee.CONFIDENTIAL);
//
//            // Create ServletSecurityElement with the constraint
            ServletSecurityElement servletSecurityElement = new ServletSecurityElement(httpConstraintElement);

//
//            // Apply the security constraint to the servlet
            securedServletRegistration.setServletSecurity(servletSecurityElement);
//
////            Servlets.securityConstraint().addRoleAllowed(ROLE_IFFAR_ADMIN)
////                    .addWebResourceCollection(Servlets.webResourceCollection().addUrlPattern("/configCSVFragment.xhtml"))
//
//            System.out.println("Programmatically added security constraint to securedServlet.");
        }
    }
}
