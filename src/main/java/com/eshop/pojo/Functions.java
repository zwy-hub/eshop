package com.eshop.pojo;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Functions implements Comparable<Functions> {
    private int id;
    private String name;
    private int parentId;
    private String url;
    private boolean isLeaf;
    private Set ais = new HashSet();

    @Override
    public int compareTo(Functions o) {
        return Integer.compare(this.getId(), (o.getId()));
    }
}
