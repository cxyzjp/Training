package com.cxy.shardingjdbc.entity.a;

public class Material {
    private Integer id;

    private Integer sellerSn;

    private String name;

    private Integer type;

    private Integer size;

    private Integer line;

    private Integer level;

    private String path;

    public Material(){}

    public Material(Integer sellerSn, String name, Integer type, Integer size, Integer line, Integer level) {
        this.sellerSn = sellerSn;
        this.name = name;
        this.type = type;
        this.size = size;
        this.line = line;
        this.level = level;
    }

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", sellerSn=" + sellerSn +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSellerSn() {
        return sellerSn;
    }

    public void setSellerSn(Integer sellerSn) {
        this.sellerSn = sellerSn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}