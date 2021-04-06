package ru.n1ppl3.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@State(value = Scope.Benchmark)
public class ListsBenchmark {

    @Param({"100000"})
    private int nStrings;

    private static String[] strings;
    private static LinkedList<String> linkedList;
    private static ArrayList<String> arrayList;

    @Setup(Level.Iteration)
    public void setup() {
        strings = new String[nStrings];
        for (int i=0; i < nStrings; i++) {
            strings[i] = "String to intern " + i;
        }
        linkedList = new LinkedList<>();
        arrayList = new ArrayList<>(nStrings);
    }

    @Benchmark
    public List<String> testLinkedListAdd() {
        for (int i=0; i < nStrings; i++) {
            linkedList.add(strings[i]);
        }
        return linkedList;
    }

    @Benchmark
    public List<String> testArrayListAdd() {
        for (int i=0; i < nStrings; i++) {
            arrayList.add(strings[i]);
        }
        return arrayList;
    }

}
