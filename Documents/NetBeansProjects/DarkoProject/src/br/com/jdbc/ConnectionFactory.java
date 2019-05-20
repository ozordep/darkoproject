package br.com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    public static Connection conector() {

        java.sql.Connection conexao = null;
        String driver = "com.mysql.jdbc.Driver"; // destinada a carregar a biblioteca do java
        String url = "jdbc:mysql://localhost:3306/darkoproject";
        String user = "root";
        String password = "";

        try {
            Class.forName(driver); //executa o Driver sql
            conexao = DriverManager.getConnection(url, user, password); //receber como retorno o gerenciamento da execução do driver com os parametros informados

            return conexao;
        } catch (Exception e) {
            return null;
        }
    }

}
