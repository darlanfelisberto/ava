package br.com.feliva.erp.model;


import br.com.feliva.sharedClass.db.Model;
import br.com.feliva.util.Constantes;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@Log4j2
public class Imagen extends Model<UUID> {

//    public static final byte[] SEM_IMAGE_BYTE = Base64.getDecoder().decode(SEM_IMAGE_BASE64);
    @Id
    @Column(name="imagen_id")
    private UUID imagenId = UUID.randomUUID();

    @Column(length = 100)
    private String nome;

    @Override
    public UUID getMMId() {
        return this.imagenId;
    }

    public String folder;
    @Transient
    private byte[] data;

    public Imagen() {}

    public void setImageRedimencionada(InputStream img) {
        try {
            int widthI = Constantes.WIDTH;
            int heigthI = Constantes.HEIGHT;
            BufferedImage imagem = ImageIO.read(img);
            widthI = imagem.getWidth();
            heigthI = imagem.getHeight();
            float maior = 0;
            if(maior < widthI) {
                maior = widthI;
            }
            if(maior < heigthI) {
                maior = heigthI;
            }
            float aumento = Constantes.WIDTH / maior;

            widthI = (int) (widthI*aumento);
            heigthI = (int) (heigthI*aumento);

            BufferedImage new_img = new BufferedImage(widthI, heigthI, BufferedImage.TYPE_USHORT_555_RGB);

            Graphics2D g = new_img.createGraphics();
            g.drawImage(imagem, 0, 0, widthI, heigthI, null);
            g.dispose();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(new_img, "png", baos);

            this.setData(baos.toByteArray());
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    public void salveFileInDisc() throws IOException {
        LocalDateTime data = LocalDateTime.now();

        this.folder = data.getYear() + File.separator + data.getDayOfYear();

        var linkFolder = Constantes.imagenPath + File.separator + this.folder + File.separator;

        Files.createDirectories(Paths.get(linkFolder));

        Path path = Paths.get(linkFolder + this.imagenId + Constantes.imagenExtencao);
        if(!Files.exists(path)) {
            Files.write(path, this.data, StandardOpenOption.CREATE_NEW);
            //apos salvar descarta dados e libera a memoria
            this.data = null;
        }else {
            this.data = null;
        }
    }

    public void deleteFileInDisc() throws IOException {
        Path path = Paths.get(getFullPath());
        if(Files.exists(path)) {
            Files.delete(path);
        }
    }

    protected String getFullPath() {
        return "/tmp/" + this.imagenId;
    }

    public byte[] getData() {
        try {
            if(this.data == null) {
                this.data = Files.readAllBytes(Path.of(this.getFullPath()));
            }
            return this.data;
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return null;
    }
}
