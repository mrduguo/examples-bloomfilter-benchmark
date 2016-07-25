## Introduction
A [Bloom filter](https://en.wikipedia.org/wiki/Bloom_filter) is a space-efficient probabilistic data structure, conceived by Burton Howard Bloom in 1970, that is used to test whether an element is a member of a set.
 
This example perform benchmark against JAVA HashSet memory consumption, it's about 100 times more [memory efficient](http://hur.st/bloomfilter?n=1000000&p=0.01).



## Run BenchMark


```sh

./gradlew
java -jar build/libs/*.jar --benchmark.storeType=guaranteed  --benchmark.numberOfElements=1000000
java -jar build/libs/*.jar --benchmark.storeType=probability --benchmark.numberOfElements=1000000
java -jar build/libs/*.jar --benchmark.storeType=guaranteed  --benchmark.numberOfElements=10000000
java -jar build/libs/*.jar --benchmark.storeType=probability --benchmark.numberOfElements=10000000

java -jar build/libs/*.jar --benchmark.storeType=probability --benchmark.numberOfElements=100000000
java -jar build/libs/*.jar --benchmark.storeType=probability --benchmark.numberOfElements=1000000000

```

The result will append to README.md for further analysis.


## Memory Consumption Analysis

| # of Elements |     HashSet     |   BloomFilter   |
|---------------|----------------:|----------------:|
|     1M        |        92 MB    |         1 MB    |
|     10M       |       941 MB    |         6 MB    |
|     100M      |                 |       115 MB    |
|     1B        |                 |      1190 MB    |


## Raw Data


| Store Type                |   # of Elements |  Memory (bytes) |  Time (seconds) |
|---------------------------|----------------:|----------------:|----------------:|
| HashSetNameKeeper         |       1,000,000 |      92,460,880 |               1 |
| BoomFilterNameKeeper      |       1,000,000 |       1,000,000 |               0 |
| HashSetNameKeeper         |      10,000,000 |     941,333,888 |              26 |
| BoomFilterNameKeeper      |      10,000,000 |       6,295,608 |               8 |
| BoomFilterNameKeeper      |     100,000,000 |     115,425,352 |              95 |
| BoomFilterNameKeeper      |   1,000,000,000 |   1,190,073,232 |            1474 |