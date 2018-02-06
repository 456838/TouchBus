package com.salton123.touchbus;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 张宇(G7428) on 2017/9/8.
 * E-mail: zhangyu4@yy.com
 * YY: 909017428
 */
public class TouchViewHolder<VIEW> {
    private Set<VIEW> set = new HashSet<>();

    public Set<VIEW> getView() {
        return set;
    }

    public void attach(VIEW v) {
        if (v != null) {
            set.add(v);
        }
    }

    public void dettach(VIEW v) {
        if (v != null) {
            set.remove(v);
        }
    }
}
