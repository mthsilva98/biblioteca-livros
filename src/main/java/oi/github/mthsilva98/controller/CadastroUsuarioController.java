package oi.github.mthsilva98.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import oi.github.mthsilva98.dao.UsuarioDAO;
import oi.github.mthsilva98.model.Usuario;

public class CadastroUsuarioController {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtTelefone;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnLimpar;

    private Usuario usuarioAtual;

    @FXML
    private void initialize() {
        btnSalvar.setOnAction(event -> salvarUsuario());
        btnAtualizar.setOnAction(event -> atualizarUsuario());
        btnExcluir.setOnAction(event -> excluirUsuario());
        btnLimpar.setOnAction(event -> limparCampos());
    }

    private void salvarUsuario() {
        String nome = txtNome.getText();
        String email = txtEmail.getText();
        String telefone = txtTelefone.getText();

        if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
            mostrarAlerta("Erro", "Todos os campos devem ser preenchidos.");
            return;
        }

        Usuario usuario = new Usuario(nome, email, telefone);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.salvar(usuario);

        mostrarAlerta("Sucesso", "Usuário salvo com sucesso!");

        limparCampos();
    }

    private void atualizarUsuario() {
        if (usuarioAtual != null) {
            usuarioAtual.setNome(txtNome.getText());
            usuarioAtual.setEmail(txtEmail.getText());
            usuarioAtual.setTelefone(txtTelefone.getText());

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.atualizar(usuarioAtual);

            mostrarAlerta("Sucesso", "Usuário atualizado com sucesso!");
        } else {
            mostrarAlerta("Erro", "Selecione um usuário para atualizar.");
        }
    }

    private void excluirUsuario() {
        if (usuarioAtual != null) {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.excluir(usuarioAtual.getId());

            mostrarAlerta("Sucesso", "Usuário excluído com sucesso!");
            limparCampos();
        } else {
            mostrarAlerta("Erro", "Selecione um usuário para excluir.");
        }
    }

    private void limparCampos() {
        txtNome.clear();
        txtEmail.clear();
        txtTelefone.clear();
        usuarioAtual = null;
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
