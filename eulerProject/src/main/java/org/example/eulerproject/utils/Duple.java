package org.example.eulerproject.utils;

import lombok.Data;

@Data
public class Duple {
    Integer rowSize;
    Integer minSize;

    public Duple(Integer rowSize, Integer columnSize) {
        setRowSize(rowSize);
        setMinSize(columnSize);
    }

    public Duple(){
    }
}
