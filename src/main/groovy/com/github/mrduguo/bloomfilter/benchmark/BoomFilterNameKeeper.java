package com.github.mrduguo.bloomfilter.benchmark;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

public class BoomFilterNameKeeper implements NameKeeper {

    private final BloomFilter bloomFilter;

    @Autowired
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
