package oi.github.mthsilva98.dao;

import oi.github.mthsilva98.model.Livro;
import oi.github.mthsilva98.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LivroDAO {

    public void salvar(Livro livro) {
        String sql = "INSERT INTO livros (titulo, autor, genero) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getGenero());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Outros métodos CRUD (atualizar, excluir, buscar) podem ser implementados aqui.
}
