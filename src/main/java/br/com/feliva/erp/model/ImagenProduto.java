package br.com.feliva.erp.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Getter
@Setter
@Entity
@Table(name = "imagen_produto")
public class ImagenProduto extends Imagen {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id")
    private Produto produto;


    public byte[] getImage64() {
        try {
            BufferedImage imagem = ImageIO.read(Files.newInputStream(Path.of(this.getFullPath())));
            BufferedImage new_img = new BufferedImage(64, 64, BufferedImage.TYPE_USHORT_555_RGB);

            Graphics2D g = new_img.createGraphics();
            g.drawImage(imagem, 0, 0, 64, 64, null);
            g.dispose();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(new_img, "png", baos);

            return baos.toByteArray();
        } catch (IOException ex) {
            // ex.printStackTrace();
            System.out.println("Imagen não encontrada. Cuidado!");
        }

        return null;
    }

}
