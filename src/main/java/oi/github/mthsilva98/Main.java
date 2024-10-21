package oi.github.mthsilva98;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            // Carregar a tela de Cadastro de Livros
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/CadastroLivro.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600); // Aumentei o tamanho da janela
            stage.setTitle("Sistema de Gerenciamento de Biblioteca - Cadastro de Livros");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
