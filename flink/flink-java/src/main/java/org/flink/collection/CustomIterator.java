package org.flink.collection;

import java.io.Serializable;
import java.util.Iterator;

/**
 * @author whh
 * @desc
 * @date 2020/12/30 16:02
 */
public class CustomIterator implements Iterator<Integer>, Serializable {

    private Integer i = 0;

    @Override
    public boolean hasNext() {
        return i < 50;
    }

    @Override
    public Integer next() {
        i++;
        return i;
    }
}
