package com.github.mrduguo.bloomfilter.benchmark;

/**
 * A generic data store to track string based names.
 */
public interface NameKeeper {

    /**
     * Add a new entry to the data store
     *
     * @param name a string based entry value
     */
    void put(String name);

    /**
     * Check if the data store contains the value or not
     * @param name a string based entry value
     * @return false only if doesn't contain the value
     */
    boolean contains(String name);
}
