package br.com.feliva.enun;

import lombok.Getter;
import software.xdev.chartjs.model.color.RGBAColor;

@Getter
public enum DBs {
    postgres("jdbc/93postgres", 0,RGBAColor.GRAY),
    siged("jdbc/93siged", 6,RGBAColor.VIOLET),
    baseArquivos("jdbc/93base_arquivos", 5,RGBAColor.BLACK),
    sitemasLog("jdbc/93sistemas_log", 4,RGBAColor.YELLOW),
    sitemasComum("jdbc/93sistemas_comum", 3,RGBAColor.GREEN),
    adminitrativo("jdbc/93administrativo", 2,RGBAColor.RED),
    sigaa("jdbc/93sigaa", 1,RGBAColor.BLUE);

    String jni;
    int   id;
    RGBAColor color;

    DBs(String jni,int id,RGBAColor color) {
        this.jni = jni;
        this.id = id;
        this.color = color;
    }
}
