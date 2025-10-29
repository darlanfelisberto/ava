package br.com.feliva.bean.produto;

import br.com.feliva.erp.model.Produto;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.omnifaces.cdi.ViewScoped;

import javax.crypto.KeyGenerator;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Named
@ViewScoped
@Log4j2
public class ProdutoViewBean implements Serializable {
    private static final long serialVersionUID = 1L;

    boolean rendBusca = true;
    boolean rendResultadoBusca = false;
    boolean rendFormProduto = false;
    boolean rendEdita = false;

    Produto.FiltroProduto filtroProduto;

    private Produto produto;

    @Inject ProdutoControlBean control;

    @PostConstruct
    public void telaBuscaProduto() {
        System.out.println("PostConstruct: ProdutoViewBean");
        this.filtroProduto = new Produto.FiltroProduto();
        this.rendBusca = true;
        this.rendResultadoBusca = false;
        this.rendFormProduto = false;
        this.rendEdita = false;
    }

    public void telaListarBusca() {
        this.rendBusca = true;
        this.rendResultadoBusca = true;
        this.rendFormProduto = false;
        this.rendEdita = false;
    }

    public void telaFormProduto() {
        if(!this.rendEdita) {
            this.filtroProduto = new Produto.FiltroProduto();
        }
        this.rendBusca = false;
        this.rendResultadoBusca = false;
        this.rendFormProduto = true;
    }

    public void telaEditarFormProduto() {
        this.rendEdita = true;
        this.telaFormProduto();
    }

    public void cancelar() {
        this.telaBuscaProduto();
        System.out.println("Cancelar: ProdutoViewBean");
    }

    public void toEditar(Integer id){
        this.produto = this.control.getProdutoDAO().findById(id);
        this.telaEditarFormProduto();
    }

    public void preparaEdicao(Integer id){

    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("preDestroy: ProdutoViewBean");
    }

    public boolean isRendBusca() {
        return rendBusca;
    }

    public boolean isRendResultadoBusca() {
        return rendResultadoBusca;
    }

    public boolean isRendFormProduto() {
        return rendFormProduto;
    }

    public boolean isRendEdita() {
        return rendEdita;
    }

    public Produto.FiltroProduto getFiltroProduto() {
        return filtroProduto;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public ProdutoControlBean getControl() {
        return control;
    }
}
