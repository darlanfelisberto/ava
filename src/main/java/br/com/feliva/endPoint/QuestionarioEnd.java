package br.com.feliva.endPoint;

import br.com.feliva.dao.questionario.QuestionarioDAO;
import br.com.feliva.erp.model.questionarios.Questionario;
import br.com.feliva.erp.model.questionarios.dto.QuestionarioDTO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestScoped
@Path("/questionario")
public class QuestionarioEnd {

    @Inject
    private QuestionarioDAO questionarioDAO;

    @Inject
    private Validator validator;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response salvar(QuestionarioDTO questionarioDTO) {


        try {
            Questionario questionario = new Questionario();
            questionarioDTO.inicialize(questionario);

            Set<ConstraintViolation<Questionario>> violations = validator.validate(questionario);
            if (!violations.isEmpty()) {
                Map<String, String> errors = violations.stream()
                        .collect(Collectors.toMap(v -> v.getPropertyPath().toString(), ConstraintViolation::getMessage));
                return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();
            }

            questionarioDAO.persistT(questionario);//todo

            Map<String, String> success = new HashMap<>();
            success.put("message", "Questionario salvo com sucesso");
            success.put("idQuestionario", questionario.getIdQuestionario().toString());
            return Response.ok(success).build();
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> error = new HashMap<>();
            error.put("error", "Erro ao salvar o questionário: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response teste(@PathParam("id") String id) {
        try {
            var q = this.questionarioDAO.findById(UUID.fromString(id));
            var dto = new QuestionarioDTO()
                    .inicialize(q)
                    .inicializeQuestoes(q.getListaQuestao());
            return Response.ok(dto).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.ok().build();
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        try {
            var q = this.questionarioDAO.listAllQuestionarios();

            return Response.ok(QuestionarioDTO.fromList(q)).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.ok().build();
    }
}
