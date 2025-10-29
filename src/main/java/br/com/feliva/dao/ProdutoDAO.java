package br.com.feliva.dao;

import br.com.feliva.erp.model.Produto;
import br.com.feliva.sharedClass.db.InjectEntityManagerDAO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.transaction.RollbackException;
import jakarta.transaction.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestScoped
public class ProdutoDAO extends InjectEntityManagerDAO<Produto> {

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
//    @Transactional
//    public void mergeT(Produto entity) throws RollbackException {
//        this.saveProdudoDb(entity);
//        if(entity.getImagenList() != null){
//            entity.getImagenList().forEach((imagenProduto) ->{
//                try {
//                    imagenProduto.salveFileInDisck();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//        }
//    }

    public List<Produto> listAll() {
        try {
            return this.em.createQuery("select p from Produto p order by p.nome asc").getResultList();
        }catch (NoResultException nre){}
        return null;
    }


}
