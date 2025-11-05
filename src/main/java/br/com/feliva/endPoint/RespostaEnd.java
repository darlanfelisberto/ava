package br.com.feliva.endPoint;

import br.com.feliva.dao.questionario.*;
import br.com.feliva.erp.model.questionarios.*;
import br.com.feliva.erp.model.questionarios.dto.RespostaQuestaoDTO;
import br.com.feliva.erp.model.questionarios.dto.RespostaQuestionarioDTO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Path("/resposta")
public class RespostaEnd {

    @Inject
    private QuestionarioDAO questionarioDAO;

    @Inject
    private RespostaQuestionarioDAO respostaQuestionarioDAO;

    @Inject
    private RespostaQuestaoDAO respostaQuestaoDAO;

    @Inject
    private AlternativaDAO alternativaDAO;

    @Inject
    private RespostaAlternativaDAO respostaAlternativaDAO;

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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response receberRespostas(RespostaQuestionarioDTO respostaQuestionarioDTO) {
        try {
            RespostaQuestionario respostaQuestionario = this.respostaQuestionarioDAO.findRespostaQuestionarioAndQuestionario(respostaQuestionarioDTO);
//            RespostaQuestao respostaQuestao = null;
            Questionario q = this.questionarioDAO.findById(respostaQuestionarioDTO.idQuestionario);
            if(q == null) {
                throw new RuntimeException("Questionario nao foi encontrado");
            }

            if (respostaQuestionario == null) { //verifica e salva a nova tentativa de resposta ao questionario

//                respostaQuestionario = respostaQuestionarioDTO.toObject();
//                respostaQuestionario.setQuestionario(this.questionarioDAO.findById(respostaQuestionarioDTO.idQuestionario));
//
//                if(respostaQuestionario.getQuestionario() == null) {
//                    throw new RuntimeException("Questionario nao foi encontrado");
//                }
//
//                this.respostaQuestionarioDAO.persistT(respostaQuestionario);
            }else {
                for (RespostaQuestaoDTO respQDTO : respostaQuestionarioDTO.listaRespostaQuestao){
                    var respostaQuestao = this.respostaQuestaoDAO.findRespostaQuestaoAndRespQuestionario(respostaQuestionario.getMMId(), respQDTO.idQuestao);
                    if(respostaQuestao == null){
                        //cria uma
                    }
                    List<Alternativa> remove = new ArrayList<>();
                    List<Alternativa> persist = new ArrayList<>();
//
//                    var respostas =  this.respostaAlternativaDAO.findAll(respostaQuestao);
                    for(var altdto : respQDTO.listaAlternativa){
                        if(respostaQuestao.getListaRespostaAlternativa() == null){
                            respostaQuestao.setListaRespostaAlternativa(new ArrayList<RespostaAlternativa>());
                        }
                        respostaQuestao.getListaRespostaAlternativa();
//                        if(altdto.isNovo()){
//                            var alternativa = this.alternativaDAO.findById(altdto.idAlternativa);
//                            var respostaAlternativa = new RespostaAlternativa(alternativa,respostaQuestao);
//                            this.respostaAlternativaDAO.persistT(respostaAlternativa);
//                        }else {
//                            //TODO: Implementar busca de RespostaAlternativa
//                        }
                    }
                }
            }
            return Response.ok(new RespostaQuestionarioDTO().inicialize(respostaQuestionario)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    public void processaListaAlternativas(){

    }

    public void processaRespostaQuestao(List<RespostaQuestaoDTO> listaRespostaQuestaoDTO){
        if(listaRespostaQuestaoDTO == null || listaRespostaQuestaoDTO.isEmpty()){
            throw new RuntimeException("Lista de Resposta Questao nao pode ser nula ou vazia");
        }
        RespostaQuestao respostaQuestao = null;
        for(var respQDTO : listaRespostaQuestaoDTO){
//            if(respQDTO)
        }
    }

}
