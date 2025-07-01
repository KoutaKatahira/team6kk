package com.example.demo.Repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //team6db全件検索メソッド
    public List<Map<String, Object>> findAll() {
        return jdbcTemplate.queryForList("SELECT * FROM List1;");
    }
    
    public List<Map<String, Object>> findAll2() {
        return jdbcTemplate.queryForList("SELECT * FROM menu ORDER BY id DESC;");
    }
    
    public int  itemSort(int sortNo) {
        int maxId = jdbcTemplate.queryForObject("SELECT MAX(id) FROM menu", Integer.class);
        return jdbcTemplate.update(
                "UPDATE menu SET id = CASE " +
                        "WHEN id = ? THEN ? " +   // ← maxId をここに渡す
                        "WHEN id > ? THEN id - 1 " +
                        "ELSE id END;",
                        sortNo, maxId, sortNo
                    );
    }

    //【チュートリアル用】
    //spitem_orderテーブル全件検索メソッド
    public List<Map<String, Object>> findOrderAll() {
    return jdbcTemplate.queryForList("SELECT * FROM spitem_order"); 
    }
    
  //データ追加用
    public int findByCategory(String name,String address,String rad1,int number1,String story,int userID) {
        return jdbcTemplate.update("INSERT INTO List1 (name, address, item, orders, message, ID) VALUES (?, ?, ?, ?, ?, ?);", name, address, rad1, number1, story, userID);
    }
  //データ削除用
    public int deleteData() {
        return jdbcTemplate.update("DELETE FROM List1;");
    }
  //ログイン用
    public int goLogin(String username,String pass) {
        String sql = "SELECT COUNT(*) FROM Login WHERE ID = ? AND Pass = ?;";
        return jdbcTemplate.queryForObject(sql, Integer.class, username, pass);
    }
  //名前検索
    public String findCall(int callNo) {
        String sql = "SELECT name FROM List1 WHERE ID = ?;";
        return jdbcTemplate.queryForObject(sql, String.class, callNo);
    }
  //名前検索
    public String findAddress(int callNo) {
        String sql = "SELECT address FROM List1 WHERE ID = ?;";
        return jdbcTemplate.queryForObject(sql, String.class, callNo);
    }
  //ログイン用
    public int goid() {
        String sql = "SELECT COUNT(*) FROM List1;";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
    public int goid2() {
        String sql = "SELECT COUNT(*) FROM Menu;";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
    public List<Map<String, Object>> sort(int quantity) {
        return jdbcTemplate.queryForList("SELECT * FROM Menu ORDER BY id DESC LIMIT ?;", quantity);
    }
    
    public int menuChange(String itemname1,int menuID) {
        int rowCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Menu", Integer.class);
        return jdbcTemplate.update("INSERT IGNORE INTO Menu VALUES (?, ?);", rowCount+1, itemname1);
    }
    
    public int deletemenu() {
        return jdbcTemplate.update("DELETE FROM Menu;");
    }
    
    public int insertmenu() {
        return jdbcTemplate.update("INSERT INTO Menu VALUES( '1' , 'カレーライス(500円)'),( '2' , 'ラーメン(500円)'),( '3' , 'ショートケーキ(500円)');");
    }

}
