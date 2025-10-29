package br.com.feliva.dao;

import br.com.feliva.erp.model.ImagenProduto;
import br.com.feliva.erp.model.Produto;
import br.com.feliva.sharedClass.db.InjectEntityManagerDAO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.transaction.RollbackException;
import jakarta.transaction.Transactional;

import java.io.IOException;
import java.util.List;

@RequestScoped
public class ImagenProdutoDAO extends InjectEntityManagerDAO<Produto> {

//    static final Map<String, LazyConsultConfig> lCConfigs = new HashMap<String, LazyConsultConfig>();
//
//    static {
//        lCConfigs.put("id", new LazyConsultConfig("", "p.idProduto","p.idProduto", MatchMode.idInteger));
//        lCConfigs.put("nome", new LazyConsultConfig("", "p.nome","p.nome", MatchMode.contains));
//    }

    //    @Transactional
//    private void saveProdudoDb(Produto entity) throws RollbackException {
//        //seta o produto novamente
//        if(entity.getImagenList() != null){
//            entity.getImagenList().forEach((imagenProduto) ->{
//                imagenProduto.setProduto(entity);
//            });
//        }
//        this.merge(entity);
//    }
//
    @Transactional
    public void mergeT(List<ImagenProduto> list) throws RollbackException {
        list.forEach((imagenProduto) -> {
            try {
                imagenProduto.salveFileInDisc();
                this.em.merge(imagenProduto);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    @Transactional
    public void mergeT(ImagenProduto ip) throws RollbackException {
        try {
            ip.salveFileInDisc();
            this.em.merge(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
