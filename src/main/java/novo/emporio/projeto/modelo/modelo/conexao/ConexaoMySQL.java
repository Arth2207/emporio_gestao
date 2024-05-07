package novo.emporio.projeto.modelo.modelo.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {

    private static final String URL = "jdbc:mysql://localhost/gestao_emporio?useTimezone=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORLD = "220720";

    public static Connection connection;

  

    public static Connection obterconexao() throws SQLException {

        if (connection == null) {
            connection = DriverManager.getConnection(URL, USER, PASSWORLD);
        }
        return connection;
    }

    public static void fecharConexao() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    
    }
    public static void main(String[] args) throws SQLException{
     System.out.println(obterconexao());
    }

}
