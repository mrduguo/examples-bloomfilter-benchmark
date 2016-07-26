package com.github.mrduguo.bloomfilter.benchmark;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;


/**
 * Bloom filter based store to track the names.
 * It will guaranteed to return true for any name seen before.
 * BUT may have chance to return true for name which was NOT seen before.
 */
public class BoomFilterNameKeeper implements NameKeeper {

    private final BloomFilter bloomFilter;

    public BoomFilterNameKeeper(long expectedInsertions, double falsePositiveProbability) {
        bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), expectedInsertions, falsePositiveProbability);
    }


    @Override
    public void put(String name) {
        bloomFilter.put(name);
    }

    @Override
    public boolean contains(String name) {
        return bloomFilter.mightContain(name);
    }
}
