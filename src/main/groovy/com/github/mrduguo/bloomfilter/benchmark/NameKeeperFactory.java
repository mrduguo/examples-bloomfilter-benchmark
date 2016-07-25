package com.github.mrduguo.bloomfilter.benchmark;

public class NameKeeperFactory {

    public static NameKeeper create(String type,long numberOfElements) {
        NameKeeper nameKeeper=null;
        if(type.equals("guaranteed")){
            nameKeeper=new HashSetNameKeeper();
        }else if(type.equals("probability")){
            nameKeeper=new BoomFilterNameKeeper(numberOfElements, 0.01);
        }
        return nameKeeper;
    }
}
