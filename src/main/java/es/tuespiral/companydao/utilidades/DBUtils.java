package es.tuespiral.companydao.utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class DBUtils {

    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        String url = "jdbc:oracle:thin:@localhost:1521/XE";
        String user = "COMPANY";
        String pass = "COMPANY";

        conn = DriverManager.getConnection(url, user, pass);
        return conn;

    }

    public static Connection getTransactionConnection() throws SQLException {
        Connection conn = getConnection();
        conn.setAutoCommit(false);
        return conn;
    }

    public static void close(Connection conn) throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    public static void transactionClose(Connection conn) throws SQLException {
        if (conn != null) {
            conn.setAutoCommit(true);
            conn.close();
        }
    }

    public static void close(Statement st) throws SQLException {
        if (st != null) {
            st.close();
        }
    }

    public static void close(ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
    }

    public static java.sql.Date toSqlDate(LocalDate lDate) {
        return java.sql.Date.valueOf(lDate);
    }

    public static java.sql.Date toSqlDate(java.util.Date uDate) {
        return new java.sql.Date(uDate.getTime());
    }

    public static java.util.Date toUtilDate(java.sql.Date sqlDate) {
        return new java.util.Date(sqlDate.getTime());
    }

    public static LocalDate toLocalDate(java.sql.Date sqlDate) {
        return sqlDate.toLocalDate();
    }

}
