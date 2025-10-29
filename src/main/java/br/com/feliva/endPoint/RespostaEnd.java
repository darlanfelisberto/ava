package br.com.feliva.endPoint;

import br.com.feliva.dao.questionario.QuestionarioDAO;
import br.com.feliva.erp.model.questionarios.dto.QuestionarioDTO;
import br.com.feliva.erp.model.questionarios.dto.RespostaQuestionarioDTO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@RequestScoped
@Path("/resposta")
public class RespostaEnd {

    @Inject
    private QuestionarioDAO questionarioDAO;


    @GET
    @Path("/questionario/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response respostaQuestionario(@PathParam("id") String id){
        try {
            var q = this.questionarioDAO.findRespostaQuetionario(Integer.parseInt(id));

            return Response.ok(new RespostaQuestionarioDTO().inicialize(q)).build();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Response.ok().build();
    }

}
