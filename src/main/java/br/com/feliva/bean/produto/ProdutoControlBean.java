package br.com.feliva.bean.produto;

import br.com.feliva.dao.CategoriaDAO;
import br.com.feliva.dao.ImagenProdutoDAO;
import br.com.feliva.dao.ProdutoDAO;
import br.com.feliva.dao.TipoUnidadeDAO;
import br.com.feliva.erp.model.Categoria;
import br.com.feliva.erp.model.ImagenProduto;
import br.com.feliva.erp.model.Produto;
import br.com.feliva.erp.model.TipoUnidade;
import br.com.feliva.util.MessagesUtil;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.RollbackException;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.omnifaces.cdi.Param;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@RequestScoped
@Log4j2
@Named
public class ProdutoControlBean extends LazyDataModel<Produto> {

    private Produto produto;

    private List<Categoria> listCategorias;
    private List<TipoUnidade> listTipoUnidade;

    @Inject
    @Param(pathIndex = 0) String tela;

    @Inject
    @Param(pathIndex = 1) Integer idProduto;

    @Inject
    ProdutoViewBean produtoViewBean;
    @Inject
    MessagesUtil messagesUtil;

    @Inject
    CategoriaDAO categoriaDAO;
    @Inject
    TipoUnidadeDAO tipoUnidadeDAO;
    @Inject
    ProdutoDAO produtoDAO;
    @Inject
    ImagenProdutoDAO imagenProdutoDAO;

    @PostConstruct
    public void init() {
        if (idProduto != null && produto == null) {
            this.produto = this.produtoDAO.findById(idProduto);
        }

        if (produto == null) {
            this.produto = new Produto();
        }
    }

    public void buscar(){
        this.messagesUtil.addSuccess("oi");
        this.produtoViewBean.telaListarBusca();
    }

    public void salvar() {
        try {
            this.produtoDAO.mergeT(this.produtoViewBean.getProduto());
            this.produtoViewBean.telaBuscaProduto();
            this.messagesUtil.addSuccess("Produto salvo com sucesso");
        } catch (RollbackException e) {
            this.messagesUtil.addError(e);
            log.error(e);
        }
    }
    public void handleFileUpload(FileUploadEvent event) {
        try {
            var ip = new ImagenProduto();
            ip.setProduto(this.produto);
            ip.setImageRedimencionada(event.getFile().getInputStream());
            this.imagenProdutoDAO.mergeT(ip);
            FacesMessage message = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Categoria> autoCompleteCategorias(String nome) {
        if(listCategorias == null) {
            listCategorias = this.categoriaDAO.listByName(nome);
        }
        return listCategorias;
    }

    public List<TipoUnidade> getListTipoUnidade() {
        if(listTipoUnidade == null) {
            this.listTipoUnidade = this.tipoUnidadeDAO.listAll();
        }
        return listTipoUnidade;
    }


    public void onRowSelect(SelectEvent<Produto> event) {
        FacesMessage msg = new FacesMessage("Customer Selected", String.valueOf(event.getObject().getMMId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    @Override
    public int count(Map<String, FilterMeta> filterBy) {
        return 30;
    }

    @Override
    public List<Produto> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        return this.produtoDAO.listAll();
    }

    public Produto getRowData(String rowKey) {

        return null;
    }

    @Override
    public String getRowKey(Produto customer) {
        return String.valueOf(customer.getMMId());
    }
}
