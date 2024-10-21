package oi.github.mthsilva98.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import oi.github.mthsilva98.dao.LivroDAO;
import oi.github.mthsilva98.model.Livro;

import java.util.List;

public class CadastroLivroController {

    @FXML
    private TextField txtTitulo;

    @FXML
    private TextField txtAutor;

    @FXML
    private TextField txtGenero;

    @FXML
    private CheckBox chkDisponivel;

    @FXML
    private TextField txtBusca;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnLimpar;

    @FXML
    private TableView<Livro> tableLivros;

    @FXML
    private TableColumn<Livro, Integer> colId;

    @FXML
    private TableColumn<Livro, String> colTitulo;

    @FXML
    private TableColumn<Livro, String> colAutor;

    @FXML
    private TableColumn<Livro, String> colGenero;

    @FXML
    private TableColumn<Livro, Boolean> colDisponivel;

    private Livro livroSelecionado;

    @FXML
    private void initialize() {
        btnSalvar.setOnAction(event -> salvarLivro());
        btnAtualizar.setOnAction(event -> atualizarLivro());
        btnExcluir.setOnAction(event -> excluirLivro());
        btnLimpar.setOnAction(event -> limparCampos());

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        colDisponivel.setCellValueFactory(new PropertyValueFactory<>("disponivel"));

        // Carrega todos os livros na inicialização
        carregarLivros();

        // Evento para capturar o clique na linha da TableView
        tableLivros.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selecionarLivro(newValue));
    }

    private void salvarLivro() {
        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        String genero = txtGenero.getText();
        boolean disponivel = chkDisponivel.isSelected();

        if (titulo.isEmpty() || autor.isEmpty() || genero.isEmpty()) {
            exibirAlerta("Erro", "Todos os campos são obrigatórios!");
            return;
        }

        Livro livro = new Livro(titulo, autor, genero, disponivel);
        LivroDAO livroDAO = new LivroDAO();
        livroDAO.salvar(livro);

        System.out.println("Livro salvo: " + titulo);
        exibirAlerta("Sucesso", "Livro salvo com sucesso!");
        limparCampos();
        carregarLivros(); // Atualiza a tabela
    }

    private void atualizarLivro() {
        if (livroSelecionado == null) {
            exibirAlerta("Erro", "Selecione um livro para atualizar.");
            return;
        }

        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        String genero = txtGenero.getText();
        boolean disponivel = chkDisponivel.isSelected();

        if (titulo.isEmpty() || autor.isEmpty() || genero.isEmpty()) {
            exibirAlerta("Erro", "Todos os campos são obrigatórios!");
            return;
        }

        livroSelecionado.setTitulo(titulo);
        livroSelecionado.setAutor(autor);
        livroSelecionado.setGenero(genero);
        livroSelecionado.setDisponivel(disponivel);

        LivroDAO livroDAO = new LivroDAO();
        livroDAO.atualizar(livroSelecionado);

        System.out.println("Livro atualizado: " + titulo);
        exibirAlerta("Sucesso", "Livro atualizado com sucesso!");
        limparCampos();
        carregarLivros(); // Atualiza a tabela
    }

    private void excluirLivro() {
        if (livroSelecionado == null) {
            exibirAlerta("Erro", "Selecione um livro para excluir.");
            return;
        }

        LivroDAO livroDAO = new LivroDAO();
        livroDAO.excluir(livroSelecionado.getId());

        System.out.println("Livro excluído: " + livroSelecionado.getTitulo());
        exibirAlerta("Sucesso", "Livro excluído com sucesso!");
        limparCampos();
        carregarLivros(); // Atualiza a tabela
    }

    private void limparCampos() {
        txtTitulo.clear();
        txtAutor.clear();
        txtGenero.clear();
        chkDisponivel.setSelected(false);
        livroSelecionado = null; // Reseta o livro selecionado
        tableLivros.getSelectionModel().clearSelection(); // Limpa a seleção na tabela
    }

    @FXML
    private void buscarPorTitulo() {
        String titulo = txtBusca.getText();
        LivroDAO livroDAO = new LivroDAO();
        List<Livro> livros = livroDAO.buscarPorTitulo(titulo);
        ObservableList<Livro> observableList = FXCollections.observableArrayList(livros);
        tableLivros.setItems(observableList);
    }

    private void selecionarLivro(Livro livro) {
        if (livro != null) {
            livroSelecionado = livro;
            txtTitulo.setText(livro.getTitulo());
            txtAutor.setText(livro.getAutor());
            txtGenero.setText(livro.getGenero());
            chkDisponivel.setSelected(livro.isDisponivel());
        }
    }

    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void carregarLivros() {
        LivroDAO livroDAO = new LivroDAO();
        List<Livro> livros = livroDAO.listarTodos();
        ObservableList<Livro> observableList = FXCollections.observableArrayList(livros);
        tableLivros.setItems(observableList);
    }
}
