package br.com.feliva.bean;

import br.com.feliva.dao.Postgres93DAO;
import br.com.feliva.dao.TupleDeadDAO;
import br.com.feliva.dao.WraparoundRiskDAO;
import br.com.feliva.enun.DBs;
import br.com.feliva.erp.model.WraparoundRiskModel;
import jakarta.annotation.Resource;
import jakarta.enterprise.concurrent.ManagedScheduledExecutorService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.faces.model.SelectItem;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import software.xdev.chartjs.model.charts.LineChart;
import software.xdev.chartjs.model.color.RGBAColor;
import software.xdev.chartjs.model.data.LineData;
import software.xdev.chartjs.model.dataset.LineDataset;
import software.xdev.chartjs.model.options.LineOptions;
import software.xdev.chartjs.model.options.Plugins;
import software.xdev.chartjs.model.options.Title;
import software.xdev.chartjs.model.options.elements.Fill;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Named
@RequestScoped
public class TupleDeadBean {

    @Inject TupleDeadDAO tDao;
    @Inject
    WraparoundRiskDAO wrDao;

    private LocalDateTime dtInicial =  LocalDateTime.now().minusDays(2);
    private String selectDB = "Todos";

    private String lineModel;

    public void autoTask(){
//        this.counter();
    }

    public void counter() {

        System.out.println("Buscando Dados 93...");
        try {
            var dao = Postgres93DAO.getInstance(DBs.sigaa);

            System.out.println(dao.getWraparoundRisk());
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<SelectItem> getDbs(){
        var itens = new ArrayList<SelectItem>();
        itens.add(new SelectItem("Todos"));
        Arrays.stream(DBs.values()).forEach(db -> {
            itens.add(new SelectItem(db.name()));
        });
        return itens;
    }


    public String createLineModel() {
        List<DBs> bancos = new ArrayList<>();
        if(this.selectDB.equals("Todos")){
            bancos = Arrays.stream(DBs.values()).toList();
        }else{
            bancos.add(DBs.valueOf((String) this.selectDB));
        }

        var lineChart = new LineChart();
        var lineData = new LineData();
        List<String> lables = null;
        lineChart.setData(lineData);

        for(DBs db : bancos){
            List<WraparoundRiskModel> dados = this.wrDao.findData(this.dtInicial,db);
            DateTimeFormatter zonedFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

            var data = dados.stream().map(WraparoundRiskModel::getDatfrozenxid).toList();
            lables = dados.stream().map(item -> item.getDt().format(zonedFormatter)).toList();

            lineData.addDataset(new LineDataset()
                    .setData((List) data)
                    .setLabel(db.getJni())
                    .setBorderColor(db.getColor())
//                    .setLineTension(0.1f)
                    .setFill(new Fill<Boolean>(false)));
            lineData.setLabels(lables);
        }

        return lineChart.setOptions(new LineOptions()
                .setResponsive(true)
                .setMaintainAspectRatio(false)
                .setPlugins(new Plugins()
                        .setTitle(new Title()
                                .setDisplay(true)
                                .setText("Idade xid")))
        ).toJson();
    }

    public String getLineModel() {
        return lineModel;
    }

    public LocalDateTime getDtInicial() {
        return dtInicial;
    }

    public void setDtInicial(LocalDateTime dtInicial) {
        this.dtInicial = dtInicial;
    }

    public String getSelectDB() {
        return selectDB;
    }

    public void setSelectDB(String selectDB) {
        this.selectDB = selectDB;
    }
}
