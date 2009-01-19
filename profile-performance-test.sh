#!/bin/sh
# sdr101-java
# Simple software-defined radio for Java.
# 
# (c) Karl-Martin Skontorp <kms@skontorp.net> ~ http://picofarad.org/
# Licensed under the GNU GPL 2.0 or later.

time java -Xprof -cp /home/kms/.m2/repository/junit/junit/4.4/junit-4.4.jar:target/classes:target/test-classes org.picofarad.sdr101.Sdr101performance
