package oi.github.mthsilva98.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
        // Adicione aqui as ações para os botões, por exemplo:
        btnSalvar.setOnAction(event -> salvarLivro());
        btnAtualizar.setOnAction(event -> atualizarLivro());
        btnExcluir.setOnAction(event -> excluirLivro());
        btnLimpar.setOnAction(event -> limparCampos());
    }

    private void salvarLivro() {
        // Código para salvar o livro
        System.out.println("Livro salvo: " + txtTitulo.getText());
    }

    private void atualizarLivro() {
        // Código para atualizar o livro
        System.out.println("Livro atualizado: " + txtTitulo.getText());
    }

    private void excluirLivro() {
        // Código para excluir o livro
        System.out.println("Livro excluído: " + txtTitulo.getText());
    }

    private void limparCampos() {
        txtTitulo.clear();
        txtAutor.clear();
        txtGenero.clear();
    }
}
