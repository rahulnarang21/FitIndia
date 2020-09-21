package com.fitness365.fitindia.models;

import java.util.List;

public class Model {
    private List<DataBean> Data;

//    {
//        "Data": [
//        {
//            "value":"Andaman and Nicobar Islands",
//                "id":86,
//                "type":1,
//                "parentid":0
//        },
//        {
//            "value":"Nicobar",
//                "id":2952,
//                "type":2,
//                "parentid":86
//        }
//        ]
//    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * value : Andaman and Nicobar Islands
         * id : 86
         * type : 1
         * parentid : 0
         */

        private String value;
        private int id;
        private int type;
        private int parentid;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getParentid() {
            return parentid;
        }

        public void setParentid(int parentid) {
            this.parentid = parentid;
        }
    }
}
