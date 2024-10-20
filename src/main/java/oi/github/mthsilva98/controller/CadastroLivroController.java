package oi.github.mthsilva98.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import oi.github.mthsilva98.dao.LivroDAO;
import oi.github.mthsilva98.model.Livro;

public class CadastroLivroController {

    @FXML
    private TextField txtTitulo;

    @FXML
    private TextField txtAutor;

    @FXML
    private TextField txtGenero;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnLimpar;

    @FXML
    private void initialize() {
        btnSalvar.setOnAction(event -> salvarLivro());
        btnAtualizar.setOnAction(event -> atualizarLivro());
        btnExcluir.setOnAction(event -> excluirLivro());
        btnLimpar.setOnAction(event -> limparCampos());
    }

    private void salvarLivro() {
        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        String genero = txtGenero.getText();

        // Cria um novo objeto Livro com os dados dos campos
        Livro livro = new Livro(titulo, autor, genero);

        // Cria uma instância do DAO para salvar o livro
        LivroDAO livroDAO = new LivroDAO();
        livroDAO.salvar(livro);

        // Confirmação no console para verificar se o método foi executado
        System.out.println("Livro salvo: " + titulo);
    }

    private void atualizarLivro() {
        System.out.println("Livro atualizado: " + txtTitulo.getText());
    }

    private void excluirLivro() {
        System.out.println("Livro excluído: " + txtTitulo.getText());
    }

    private void limparCampos() {
        txtTitulo.clear();
        txtAutor.clear();
        txtGenero.clear();
    }
}
