package com.bai.gmall.entity;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * (PmsBaseCatalog3)实体类
 *
 * @author makejava
 * @since 2020-06-08 19:09:37
 */
public class PmsBaseCatalog3 implements Serializable {
    /**
     * 编号
     */
    @Id
    private String id;
    /**
     * 三级分类名称
     */
    private String name;
    /**
     * 二级分类编号
     */
    private String catalog2Id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatalog2Id() {
        return catalog2Id;
    }

    public void setCatalog2Id(String catalog2Id) {
        this.catalog2Id = catalog2Id;
    }
}