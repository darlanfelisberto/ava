package br.com.feliva.erp.model;

import br.com.feliva.sharedClass.db.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "produto")
public class Produto extends Model<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    protected Integer idProduto;

    @Column(length = 100)
    @NotNull(message = "Informe um nome.")
    protected String nome;

    @Column(length = 500)
    private String descricaoCurta;

    @Column(length = 1000,columnDefinition = "text")
    private String descricaoLonga;

    @JsonDeserialize()
    @Column(name = "valor_venda")
    private BigDecimal valorVenda;

    @Column(name = "valor_custo")
    private BigDecimal valorCusto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unidade_venda")
    private TipoUnidade unidadeVenda;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unidade_compra")
    private TipoUnidade unidadeCompra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_marca")
    private Marca marca;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "produtos_tags",
            joinColumns = @JoinColumn(name = "id_produto"),
            inverseJoinColumns = @JoinColumn(name = "id_tag")
    )
    private List<Tags> listaTags;

    @Column(name = "codigo_barras")
    private Long codigoBarras;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "id_status")
    @NotNull(message = "Informe um status.")
    private Status status;

//    @NotNull(message = "Informe uma condição.")
//    @Enumerated(EnumType.STRING)
//    @Column(length = 3,name = "tipo_condicao")
//    private TipoCondicao tipoCondicao;

    @Column(name = "peso_liquido")
    private Float pesoLiquedo;

    @Column(name = "peso_bruto")
    private Float pesoBruto;

    private Float largura;
    private Float altura;
    private Float profundidade;
    private Integer volume;//quantos pacotes para ser entregue

//    @NotNull(message = "Informe a unidade de medidas.")
//    @Enumerated(EnumType.STRING)
//    @Column(length = 3,name = "tipo_medida")
//    private TipoMedida tipoMedida;

    @Column(name = "link_video", length = 150)
    private String linkVideo;

    @Column( length = 150)
    private String observacao;

    @OneToMany(mappedBy = "produto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<ImagenProduto> imagenList;

    @Column(name = "dt_validade")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")// from localdatatime
    private LocalDate dtValidade;

    @Column(name = "cod_sku")
    private String codSku;

    private String modelo;

    public Integer getMMId() {
        return this.idProduto;
    }

    @Getter
    @Setter
    public static class FiltroProduto implements Serializable {
        private Status status;
        private String nome;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricaoCurta() {
        return descricaoCurta;
    }

    public void setDescricaoCurta(String descricaoCurta) {
        this.descricaoCurta = descricaoCurta;
    }

    public String getDescricaoLonga() {
        return descricaoLonga;
    }

    public void setDescricaoLonga(String descricaolonga) {
        this.descricaoLonga = descricaolonga;
    }

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(BigDecimal valorVenda) {
        this.valorVenda = valorVenda;
    }

    public BigDecimal getValorCusto() {
        return valorCusto;
    }

    public void setValorCusto(BigDecimal valorCusto) {
        this.valorCusto = valorCusto;
    }

    public TipoUnidade getUnidadeVenda() {
        return unidadeVenda;
    }

    public void setUnidadeVenda(TipoUnidade unidadeVenda) {
        this.unidadeVenda = unidadeVenda;
    }

    public TipoUnidade getUnidadeCompra() {
        return unidadeCompra;
    }

    public void setUnidadeCompra(TipoUnidade unidadeCompra) {
        this.unidadeCompra = unidadeCompra;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Tags> getListaTags() {
        return listaTags;
    }

    public void setListaTags(List<Tags> listaTags) {
        this.listaTags = listaTags;
    }

    public Long getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(Long codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Float getPesoLiquedo() {
        return pesoLiquedo;
    }

    public void setPesoLiquedo(Float pesoLiquedo) {
        this.pesoLiquedo = pesoLiquedo;
    }

    public Float getPesoBruto() {
        return pesoBruto;
    }

    public void setPesoBruto(Float pesoBruto) {
        this.pesoBruto = pesoBruto;
    }

    public Float getLargura() {
        return largura;
    }

    public void setLargura(Float largura) {
        this.largura = largura;
    }

    public Float getAltura() {
        return altura;
    }

    public void setAltura(Float altura) {
        this.altura = altura;
    }

    public Float getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(Float profundidade) {
        this.profundidade = profundidade;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public String getLinkVideo() {
        return linkVideo;
    }

    public void setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public List<ImagenProduto> getImagenList() {
        return imagenList;
    }

    public void setImagenList(List<ImagenProduto> imagenList) {
        this.imagenList = imagenList;
    }

    public LocalDate getDtValidade() {
        return dtValidade;
    }

    public void setDtValidade(LocalDate dtValidade) {
        this.dtValidade = dtValidade;
    }

    public String getCodSku() {
        return codSku;
    }

    public void setCodSku(String codSku) {
        this.codSku = codSku;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
