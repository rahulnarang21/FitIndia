package com.fitness365.fitindia.models;

public class Category {

    /**
     * term_id : 6751
     * name : AFC Women's Football Day powered by FIT India
     * parent : 6542
     * post_count : 85
     * status : 1
     * bannerurl :
     */

    private int term_id;
    private String name;
    private int parent;
    private int post_count;

    public Category(int term_id, String bannerurl) {
        this.term_id = term_id;
        this.bannerurl = bannerurl;
    }

    private String status;

    public Category(int term_id, String name, int parent, int post_count, String status, String bannerurl) {
        this.term_id = term_id;
        this.name = name;
        this.parent = parent;
        this.post_count = post_count;
        this.status = status;
        this.bannerurl = bannerurl;
    }



    private String bannerurl;

    public int getTerm_id() {
        return term_id;
    }

    public void setTerm_id(int term_id) {
        this.term_id = term_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getPost_count() {
        return post_count;
    }

    public void setPost_count(int post_count) {
        this.post_count = post_count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBannerurl() {
        return bannerurl;
    }

    public void setBannerurl(String bannerurl) {
        this.bannerurl = bannerurl;
    }
}
