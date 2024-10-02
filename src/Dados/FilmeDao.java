package Dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class FilmeDao {

    Connection conn;
    PreparedStatement st;
    ResultSet rs;

    public boolean conectar() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cenaflix", "root", "T#7aB3$0m2@i");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return false;
        }
    }

    public int cadastrar(Filme filme) {
        int status;
        try {
            st = conn.prepareStatement("insert into filmes (nome,data_lancamento, categoria) values(?,?,?)");
            st.setString(1, filme.getNome());
            st.setString(2, filme.getData());
            st.setString(3, filme.getCategoria());
            status = st.executeUpdate();
            return status;
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }

    }

    public void desconectar() {
        try {
            conn.close();
        } catch (SQLException ex) {

        }
    }

}
