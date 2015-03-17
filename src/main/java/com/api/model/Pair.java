package com.api.model;

/**
 * Created by hevilavio on 3/17/15.
 */
public class Pair<A, B> {

    private A a;

    private B b;

    public Pair(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public A getA() {
        return a;
    }

    public B getB() {
        return b;
    }

}
