#!/bin/sh

time java -Xprof -cp /home/kms/.m2/repository/junit/junit/4.4/junit-4.4.jar:target/classes:target/test-classes org.picofarad.sdr101.Sdr101performance
