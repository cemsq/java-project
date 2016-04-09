package com.generics;

import java.util.List;

public class Result<T> {

    T get(T t) {
        return t;
    }
}

class ListResult<T> extends Result<List<T>> {
    @Override
    List<T> get(List<T> ts) {
        return super.get(ts);
    }
}


