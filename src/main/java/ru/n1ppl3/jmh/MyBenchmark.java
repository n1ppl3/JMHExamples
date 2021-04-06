package ru.n1ppl3.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.ConcurrentHashMap;

/**
 * можно запускать как через плагин, так и посредством MyJmhLauncher
 */
@State(value = Scope.Benchmark)
public class MyBenchmark {

    @Param({"10000"})
    private int nStrings;

    private static ConcurrentHashMap<String,String> map;
    private static String[] strings;

    @Setup(Level.Iteration)
    public void setup() {
        strings = new String[nStrings];
        for (int i=0; i < nStrings; i++) {
            strings[i] = new String("String to intern " + i);
        }
        map = new ConcurrentHashMap<>();
    }

    @Benchmark
    public void testIntern(Blackhole bh) {
        for (int i=0; i < nStrings; i++) {
            String t = strings[i].intern();
            bh.consume(t);
        }
    }

    @Benchmark
    public void testMap(Blackhole bh) {
        for (int i=0; i < nStrings; i++) {
            String s = strings[i];
            String t = map.putIfAbsent(s, s);
            bh.consume(t);
        }
    }

}
