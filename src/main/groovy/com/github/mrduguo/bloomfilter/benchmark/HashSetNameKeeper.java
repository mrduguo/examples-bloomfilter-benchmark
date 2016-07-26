package com.github.mrduguo.bloomfilter.benchmark;

import java.util.HashSet;
import java.util.Set;

/**
 * HashSet based store to support 100% guaranteed answer on each elements
 */
public class HashSetNameKeeper implements NameKeeper {

    private Set<String> elements = new HashSet<String>();

    @Override
    public void put(String name) {
        elements.add(name);
    }

    @Override
    public boolean contains(String name) {
        return elements.contains(name);
    }
}
