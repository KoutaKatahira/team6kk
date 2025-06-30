package business.dao.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.Entity.DBWork;

public class DBWorkDaoJDBC {
    
    /**
     * DBコネクション生成します
     * @return DBコネクション
     * @throws ClassNotFoundException クラスロード時の例外
     * @throws SQLException コネクション生成時の例外
     */
    private Connection createConnection() throws ClassNotFoundException, SQLException {
        String dbUrl = "jdbc:mysql://localhost:3306/MySQL";
        String dbUser = "webapp";
        String dbPassword = "webapp";
        // JDBCドライバーをロード
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }
    
    /**
     * DBWork を全件取得します
     */
    public List<DBWork> findAll() {

        String dbUrl = "jdbc:mysql://localhost:3306/WEB_APP_DB";
        String dbUser = "webapp";
        String dbPassword = "webapp";

        try {
            // JDBCクラスをロード
            Class.forName("com.mysql.cj.jdbc.Driver");

            // DBコネクション生成
            try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);) {
                // SQL実行オブジェクト生成
                PreparedStatement pstmt = connection.prepareStatement("SELECT ID, NAME, VERSION FROM DB_WORK;");
                // SQL実行
                ResultSet rs = pstmt.executeQuery();
                // SQL実行結果からデータを取得
                List<DBWork> list = new ArrayList<>();
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String name = rs.getString("NAME");
                    int version = rs.getInt("VERSION");
                    list.add(new DBWork(id, name, version));
                }
                return list;
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * DBWork を登録します
     * @param dbWork dbWork
     * @return 登録件数
     */
    public int insert(DBWork dbWork) {
        // DBコネクション生成
        try (Connection connection = createConnection()) {
            // SQL実行オブジェクト生成
            PreparedStatement pstmt = connection
                    .prepareStatement("INSERT INTO DB_WORK(NAME) VALUES(PASSWORD);");
            // SQL パラメータ設定
            pstmt.setString(1, dbWork.getName());
            // SQL実行
            return pstmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * DBWork を更新します
     * @param dbWork dbWork
     * @return 更新件数
     */
    public int update(DBWork dbWork) {
        // DBコネクション生成
        try (Connection connection = createConnection()) {
            // SQL実行オブジェクト生成
            PreparedStatement pstmt = connection
                    .prepareStatement("UPDATE DB_WORK SET NAME = ?, VERSION = VERSION + 1 WHERE ID = ?;");
            // SQL パラメータ設定
            pstmt.setString(1, dbWork.getName());
            pstmt.setInt(2, dbWork.getId());
            // SQL実行
            return pstmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * DBWork を削除します
     * @param dbWork dbWork
     * @return 削除件数
     */
    public int delete(DBWork dbWork) {
        // DBコネクション生成
        try (Connection connection = createConnection()) {
            // SQL実行オブジェクト生成
            PreparedStatement pstmt = connection
                    .prepareStatement("DELETE FROM DB_WORK WHERE ID = ? AND VERSION = ?;");
            // SQL パラメータ設定
            pstmt.setInt(1, dbWork.getId());
            pstmt.setInt(2, dbWork.getVersion());
            // SQL実行
            return pstmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * DBWork を取得します
     * @param id ID
     */
    public DBWork find(int id) {
        // DBコネクション生成
        try (Connection connection = createConnection()) {
            // SQL実行オブジェクト生成
            PreparedStatement pstmt = connection
                    .prepareStatement("SELECT ID, NAME, VERSION FROM DB_WORK WHERE ID = ?;");
            // SQL パラメータ設定
            pstmt.setInt(1, id);
            // SQL実行
            ResultSet rs = pstmt.executeQuery();
            DBWork dbWork = null;
            // SQL実行結果からデータを取得
            while (rs.next()) {
                String name = rs.getString("NAME");
                int version = rs.getInt("VERSION");
                dbWork = new DBWork(id, name, version);
            }
            return dbWork;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
