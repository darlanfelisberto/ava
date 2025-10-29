package br.com.feliva.servlet;

import br.com.feliva.util.Constantes;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

@WebServlet(urlPatterns = "/api/imagens/*")
public class ImagemServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private String caminhoBaseImagens = "/caminho/real/no/servidor/imagens"; // Configure o caminho base

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getPathInfo() == null || request.getPathInfo().equals("/") || !request.getPathInfo().matches("(/[0-9]{4}/[0-9]{3}/[0-9a-f-]{36}$)")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Nome da pasta/imagem não especificados, ou caracteres não permitidos.");
            return;
        }

        // pathInfo virá com uma barra inicial, por exemplo: "/minha_pasta/imagem.jpg"
        // Removemos a barra inicial para formar o caminho do arquivo

        File imagem = new File(Constantes.imagenPath, request.getPathInfo() + ".png");

        if (!imagem.exists() || imagem.isDirectory()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // Imagem não encontrada
            return;
        }

        // Define o tipo de conteúdo (MIME type) da resposta
        String contentType = getServletContext().getMimeType(imagem.getName());
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        response.setContentType(contentType);

        // Escreve o conteúdo da imagem na resposta
        response.setContentLength((int) imagem.length());
        Files.copy(imagem.toPath(), response.getOutputStream());
    }
}