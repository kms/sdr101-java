#!/bin/sh

java -Xprof=help -cp /home/kms/.m2/repository/junit/junit/4.4/junit-4.4.jar:target/classes:target/test-classes org.junit.runner.JUnitCore org.picofarad.sdr101.$1
