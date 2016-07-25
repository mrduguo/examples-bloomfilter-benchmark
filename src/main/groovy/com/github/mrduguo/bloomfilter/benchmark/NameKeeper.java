package com.github.mrduguo.bloomfilter.benchmark;

public interface NameKeeper {
    void put(String name);
    boolean contains(String name);
}
