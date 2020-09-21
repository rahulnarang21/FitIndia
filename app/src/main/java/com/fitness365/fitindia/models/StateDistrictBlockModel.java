package com.fitness365.fitindia.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StateDistrictBlockModel {

    @SerializedName("Data")
    private List<Result> data;

    public List<Result> getData() {
        return data;
    }

    public void setData(List<Result> data) {
        this.data = data;
    }


    public static class Result{
        @SerializedName("id")
        private int id;
        @SerializedName("value")
        private String value;
        @SerializedName("parentid")
        private int parentId;
        @SerializedName("type")
        private int type;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }



    }

//    private List<Model.DataBean> Data;
//
////    {
////        "Data": [
////        {
////            "value":"Andaman and Nicobar Islands",
////                "id":86,
////                "type":1,
////                "parentid":0
////        },
////        {
////            "value":"Nicobar",
////                "id":2952,
////                "type":2,
////                "parentid":86
////        }
////        ]
////    }
//
//    public List<Model.DataBean> getData() {
//        return Data;
//    }
//
//    public void setData(List<Model.DataBean> Data) {
//        this.Data = Data;
//    }
//
//    public static class DataBean {
//        /**
//         * value : Andaman and Nicobar Islands
//         * id : 86
//         * type : 1
//         * parentid : 0
//         */
//
//        private String value;
//        private int id;
//        private int type;
//        private int parentid;
//
//        public String getValue() {
//            return value;
//        }
//
//        public void setValue(String value) {
//            this.value = value;
//        }
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public int getType() {
//            return type;
//        }
//
//        public void setType(int type) {
//            this.type = type;
//        }
//
//        public int getParentid() {
//            return parentid;
//        }
//
//        public void setParentid(int parentid) {
//            this.parentid = parentid;
//        }
//    }
}
