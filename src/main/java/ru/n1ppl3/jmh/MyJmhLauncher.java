package ru.n1ppl3.jmh;

import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class MyJmhLauncher {

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
            .include(".*" + MyBenchmark.class.getSimpleName() + ".*")
            .forks(2)
            .resultFormat(ResultFormatType.TEXT)
            .warmupIterations(5)
            .measurementIterations(7)
            .build();

        new Runner(opt).run();
    }
}
