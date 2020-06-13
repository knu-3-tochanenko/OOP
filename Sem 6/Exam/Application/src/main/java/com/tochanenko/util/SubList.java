package com.tochanenko.util;

import java.util.ArrayList;
import java.util.List;

public class SubList {
    public float size;
    public List<Float> subList;

    public SubList() {
        this(0, new ArrayList<>());
    }

    public SubList(float size, List<Float> subList) {
        this.size = size;
        this.subList = subList;
    }
}