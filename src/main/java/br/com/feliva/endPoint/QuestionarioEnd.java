package br.com.feliva.endPoint;

import br.com.feliva.dao.questionario.QuestionarioDAO;
import br.com.feliva.erp.model.questionarios.RespostaQuestionario;
import br.com.feliva.erp.model.questionarios.dto.QuestaoDTO;
import br.com.feliva.erp.model.questionarios.dto.QuestionarioDTO;
import br.com.feliva.erp.model.questionarios.dto.RespostaQuestaoDTO;
import br.com.feliva.erp.model.questionarios.dto.RespostaQuestionarioDTO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@RequestScoped
@Path("/questionario")
public class QuestionarioEnd  {

    @Inject
    private QuestionarioDAO questionarioDAO;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response teste(@PathParam("id") String id){
        try {
            var q = this.questionarioDAO.findById(Integer.parseInt(id));
            var dto = new QuestionarioDTO()
                    .inicialize(q)
                    .inicializeQuestoes(q.getListaQuestao());
            return Response.ok(dto).build();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Response.ok().build();
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        try {
            var q = this.questionarioDAO.listAllQuestionarios();

            return Response.ok(QuestionarioDTO.fromList(q)).build();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Response.ok().build();
    }
}
