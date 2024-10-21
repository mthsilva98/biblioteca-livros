package oi.github.mthsilva98.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import oi.github.mthsilva98.dao.EmprestimoDAO;
import oi.github.mthsilva98.dao.LivroDAO;
import oi.github.mthsilva98.dao.UsuarioDAO;
import oi.github.mthsilva98.model.Emprestimo;
import oi.github.mthsilva98.model.Livro;
import oi.github.mthsilva98.model.Usuario;

import java.time.LocalDate;
import java.util.List;

public class CadastroEmprestimoController {

    @FXML
    private ComboBox<Usuario> cmbUsuario;

    @FXML
    private ComboBox<Livro> cmbLivro;

    @FXML
    private DatePicker dateEmprestimo;

    @FXML
    private DatePicker dateDevolucao;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnDevolver; // Novo botão para devolver

    @FXML
    private Button btnLimpar;

    private Emprestimo emprestimoAtual;

    @FXML
    private void initialize() {
        carregarUsuarios();
        carregarLivros();

        btnSalvar.setOnAction(event -> salvarEmprestimo());
        btnAtualizar.setOnAction(event -> atualizarEmprestimo());
        btnExcluir.setOnAction(event -> excluirEmprestimo());
        btnDevolver.setOnAction(event -> devolverEmprestimo()); // Evento para o botão de devolução
        btnLimpar.setOnAction(event -> limparCampos());
    }

    private void carregarUsuarios() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        List<Usuario> usuarios = usuarioDAO.listarTodos();
        ObservableList<Usuario> usuariosList = FXCollections.observableArrayList(usuarios);
        cmbUsuario.setItems(usuariosList);
    }

    private void carregarLivros() {
        LivroDAO livroDAO = new LivroDAO();
        List<Livro> livros = livroDAO.listarTodos();
        ObservableList<Livro> livrosList = FXCollections.observableArrayList(livros);
        cmbLivro.setItems(livrosList);
    }

    private void salvarEmprestimo() {
        Usuario usuario = cmbUsuario.getValue();
        Livro livro = cmbLivro.getValue();
        LocalDate dataEmprestimo = dateEmprestimo.getValue();
        LocalDate dataDevolucao = dateDevolucao.getValue();

        if (usuario == null || livro == null || dataEmprestimo == null || dataDevolucao == null) {
            mostrarAlerta("Erro", "Todos os campos devem ser preenchidos.");
            return;
        }

        Emprestimo emprestimo = new Emprestimo(usuario, livro, dataEmprestimo, dataDevolucao);
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
        emprestimoDAO.salvar(emprestimo);

        mostrarAlerta("Sucesso", "Empréstimo salvo com sucesso!");
        limparCampos();
    }

    private void atualizarEmprestimo() {
        if (emprestimoAtual != null) {
            emprestimoAtual.setUsuario(cmbUsuario.getValue());
            emprestimoAtual.setLivro(cmbLivro.getValue());
            emprestimoAtual.setDataEmprestimo(dateEmprestimo.getValue());
            emprestimoAtual.setDataDevolucao(dateDevolucao.getValue());

            EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
            emprestimoDAO.atualizar(emprestimoAtual);

            mostrarAlerta("Sucesso", "Empréstimo atualizado com sucesso!");
        } else {
            mostrarAlerta("Erro", "Selecione um empréstimo para atualizar.");
        }
    }

    private void excluirEmprestimo() {
        if (emprestimoAtual != null) {
            EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
            emprestimoDAO.excluir(emprestimoAtual.getId());

            mostrarAlerta("Sucesso", "Empréstimo excluído com sucesso!");
            limparCampos();
        } else {
            mostrarAlerta("Erro", "Selecione um empréstimo para excluir.");
        }
    }

    private void devolverEmprestimo() {
        if (emprestimoAtual != null) {
            emprestimoAtual.setDevolvido(true);

            EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
            emprestimoDAO.atualizar(emprestimoAtual);

            mostrarAlerta("Sucesso", "Livro devolvido com sucesso!");
            limparCampos();
        } else {
            mostrarAlerta("Erro", "Selecione um empréstimo para devolver.");
        }
    }

    private void limparCampos() {
        cmbUsuario.setValue(null);
        cmbLivro.setValue(null);
        dateEmprestimo.setValue(null);
        dateDevolucao.setValue(null);
        emprestimoAtual = null;
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
