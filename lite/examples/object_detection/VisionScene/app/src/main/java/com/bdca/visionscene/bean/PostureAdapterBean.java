package com.bdca.visionscene.bean;

public class PostureAdapterBean {
    private String name;
    private int Type;
    private int iconChild;
    private int iconParent;

    public PostureAdapterBean(String name, int iconChild) {
        this.name = name;
        this.iconChild = iconChild;
    }

    public int getIconChild() {
        return iconChild;
    }

    public void setIconChild(int iconChild) {
        this.iconChild = iconChild;
    }

    public int getIconParent() {
        return iconParent;
    }

    public void setIconParent(int iconParent) {
        this.iconParent = iconParent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }
}
