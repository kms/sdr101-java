#!/bin/sh
# sdr101-java
# Simple software-defined radio for Java.
# 
# (c) Karl-Martin Skontorp <kms@skontorp.net> ~ http://22pf.org/
# Licensed under the GNU GPL 2.0 or later.

java -Xprof=help -cp /home/kms/.m2/repository/junit/junit/4.4/junit-4.4.jar:target/classes:target/test-classes org.junit.runner.JUnitCore org.picofarad.sdr101.$1
