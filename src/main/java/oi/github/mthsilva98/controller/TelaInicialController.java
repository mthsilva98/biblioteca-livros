package oi.github.mthsilva98.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaInicialController {

    @FXML
    private Button btnLivros;

    @FXML
    private Button btnUsuarios;

    @FXML
    private Button btnEmprestimos;

    @FXML
    private void initialize() {
        btnLivros.setOnAction(event -> abrirTela("/fxml/CadastroLivro.fxml"));
        btnUsuarios.setOnAction(event -> abrirTela("/fxml/CadastroUsuario.fxml"));
        btnEmprestimos.setOnAction(event -> abrirTela("/fxml/CadastroEmprestimo.fxml"));
    }

    private void abrirTela(String caminhoFXML) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(caminhoFXML));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Gerenciamento de Biblioteca");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
