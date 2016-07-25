package com.github.mrduguo.bloomfilter.benchmark;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;

@Component
class BenchMarkRunner implements CommandLineRunner{

    @Value("${benchmark.numberOfElements:1000}")
    private long numberOfElements;

    @Value("${benchmark.storeType:probability}")
    private String storeType;

    private NameKeeper nameKeeper;

    public void run(String... args) {
        System.gc();
        long timeStart=System.currentTimeMillis();
        long memoryStart=ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed();
        nameKeeper=NameKeeperFactory.create(storeType,numberOfElements);
        populateData();

        System.gc();
        ensureElementsAreThere();
        generateReport(timeStart,memoryStart);
    }

    private void populateData(){
        for (long i = 0; i < numberOfElements; i++) {
            nameKeeper.put(Long.toString(i));
        }
    }

    private void generateReport(long timeStart,long memoryStart) {
        String format="| %1$-25s | %2$,15d | %3$,15d | %4$15d |";
        long timeSpent=System.currentTimeMillis()-timeStart;
        long memoryUsed=ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed()-memoryStart;
        if(memoryUsed<0){
            memoryUsed=0;
        }
        String result = String.format(format, nameKeeper.getClass().getSimpleName(), numberOfElements, memoryUsed,timeSpent/1000);
        System.out.println(result);
        try
        {
            FileWriter fw = new FileWriter("README.md",true);
            fw.write("\n");
            fw.write(result);
            fw.close();
        }
        catch(IOException e)
        {
            throw new RuntimeException("failed to write result to file",e);
        }
    }


    private void ensureElementsAreThere() {
        assert nameKeeper.contains("1");
        assert nameKeeper.contains(String.valueOf(numberOfElements));
        assert !nameKeeper.contains(String.valueOf(numberOfElements+1));
    }
}

