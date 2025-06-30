package com.example.demo.Entity;

import java.util.ArrayList;
import java.util.List;

public class MyData {
    private ResultSet resultSet;


    public static class ResultSet {
        private String apiVersion;
        private String engineVersion;
        private List<Point> point = new ArrayList<>(); // 初期化を追加

        public String getApiVersion() {
            return apiVersion;
        }

        public void setApiVersion(String apiVersion) {
            this.apiVersion = apiVersion;
        }

        public String getEngineVersion() {
            return engineVersion;
        }

        public void setEngineVersion(String engineVersion) {
            this.engineVersion = engineVersion;
        }

        public List<Point> getPoint() {
            return point;
        }

        public void setPoint(List<Point> point) {
            this.point = point;
        }
    }

    public static class Point {
        private Station station = new Station(); // 初期化を追加
        private Prefecture prefecture = new Prefecture(); // 初期化を追加

        public Station getStation() {
            return station;
        }

        public void setStation(Station station) {
            this.station = station;
        }

        public Prefecture getPrefecture() {
            return prefecture;
        }

        public void setPrefecture(Prefecture prefecture) {
            this.prefecture = prefecture;
        }
    }

    public static class Station {
        private String code;
        private String name;
        private Object type;
        private String yomi;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public String getYomi() {
            return yomi;
        }

        public void setYomi(String yomi) {
            this.yomi = yomi;
        }
    }

    public static class Prefecture {
        private String code;
        private String name;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }
}

