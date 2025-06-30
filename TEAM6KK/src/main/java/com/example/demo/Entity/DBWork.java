package com.example.demo.Entity;

public class DBWork {

    /** ID */
    private Integer id;

    /** 名前 */
    private String name;

    /** バージョン */
    private Integer version;

    /**
     * コンストラクタ
     */
    public DBWork() {
    }

    /**
     * コンストラクタ
     * @param id ID
     * @param name 名前
     * @param version バージョン
     */
    public DBWork(Integer id, String name, Integer version) {
        this.id = id;
        this.name = name;
        this.version = version;
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id セットする id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name セットする name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return version
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version セットする version
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

}