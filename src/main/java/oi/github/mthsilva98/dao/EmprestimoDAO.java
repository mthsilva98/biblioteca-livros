package oi.github.mthsilva98.dao;

import oi.github.mthsilva98.model.Emprestimo;
import oi.github.mthsilva98.model.Livro;
import oi.github.mthsilva98.model.Usuario;
import oi.github.mthsilva98.util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO {

    // Método para salvar um novo empréstimo
    public void salvar(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimos (usuario_id, livro_id, data_emprestimo, data_devolucao, devolvido) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, emprestimo.getUsuario().getId());
            stmt.setInt(2, emprestimo.getLivro().getId());
            stmt.setDate(3, Date.valueOf(emprestimo.getDataEmprestimo()));
            stmt.setDate(4, Date.valueOf(emprestimo.getDataDevolucao()));
            stmt.setBoolean(5, emprestimo.isDevolvido());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    emprestimo.setId(generatedKeys.getInt(1));
                }
            }

            System.out.println("Empréstimo salvo com ID: " + emprestimo.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar um empréstimo existente
    public void atualizar(Emprestimo emprestimo) {
        String sql = "UPDATE emprestimos SET usuario_id = ?, livro_id = ?, data_emprestimo = ?, data_devolucao = ?, devolvido = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, emprestimo.getUsuario().getId());
            stmt.setInt(2, emprestimo.getLivro().getId());
            stmt.setDate(3, Date.valueOf(emprestimo.getDataEmprestimo()));
            stmt.setDate(4, Date.valueOf(emprestimo.getDataDevolucao()));
            stmt.setBoolean(5, emprestimo.isDevolvido());
            stmt.setInt(6, emprestimo.getId());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Empréstimo atualizado com sucesso!");
            } else {
                System.out.println("Nenhum empréstimo encontrado com o ID: " + emprestimo.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir um empréstimo pelo ID
    public void excluir(int id) {
        String sql = "DELETE FROM emprestimos WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Empréstimo excluído com sucesso!");
            } else {
                System.out.println("Nenhum empréstimo encontrado com o ID: " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar um empréstimo pelo ID
    public Emprestimo buscarPorId(int id) {
        String sql = "SELECT * FROM emprestimos WHERE id = ?";
        Emprestimo emprestimo = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int usuarioId = rs.getInt("usuario_id");
                int livroId = rs.getInt("livro_id");
                LocalDate dataEmprestimo = rs.getDate("data_emprestimo").toLocalDate();
                LocalDate dataDevolucao = rs.getDate("data_devolucao").toLocalDate();
                boolean devolvido = rs.getBoolean("devolvido");

                UsuarioDAO usuarioDAO = new UsuarioDAO();
                LivroDAO livroDAO = new LivroDAO();

                Usuario usuario = usuarioDAO.buscarPorId(usuarioId);
                Livro livro = livroDAO.buscarPorId(livroId);

                emprestimo = new Emprestimo(id, usuario, livro, dataEmprestimo, dataDevolucao, devolvido);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emprestimo;
    }

    // Método para listar todos os empréstimos
    public List<Emprestimo> listarTodos() {
        String sql = "SELECT * FROM emprestimos";
        List<Emprestimo> emprestimos = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int usuarioId = rs.getInt("usuario_id");
                int livroId = rs.getInt("livro_id");
                LocalDate dataEmprestimo = rs.getDate("data_emprestimo").toLocalDate();
                LocalDate dataDevolucao = rs.getDate("data_devolucao").toLocalDate();
                boolean devolvido = rs.getBoolean("devolvido");

                UsuarioDAO usuarioDAO = new UsuarioDAO();
                LivroDAO livroDAO = new LivroDAO();

                Usuario usuario = usuarioDAO.buscarPorId(usuarioId);
                Livro livro = livroDAO.buscarPorId(livroId);

                Emprestimo emprestimo = new Emprestimo(id, usuario, livro, dataEmprestimo, dataDevolucao, devolvido);
                emprestimos.add(emprestimo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emprestimos;
    }
}
