package com.wxy.stringinternationalization.easy;

public
class LanguageBean {


    private String id;
    private String english;
    private String simplifiedChinese;
    private String traditionalChinese;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getSimplifiedChinese() {
        return simplifiedChinese;
    }

    public void setSimplifiedChinese(String simplifiedChinese) {
        this.simplifiedChinese = simplifiedChinese;
    }

    public String getTraditionalChinese() {
        return traditionalChinese;
    }

    public void setTraditionalChinese(String traditionalChinese) {
        this.traditionalChinese = traditionalChinese;
    }
}
