#!/bin/sh
# sdr101-java
# Simple software-defined radio for Java.
# 
# (c) Karl-Martin Skontorp <kms@skontorp.net> ~ http://picofarad.org/
# Licensed under the GNU GPL 2.0 or later.

java -cp target/classes:target/test-classes org.picofarad.sdr101.Sdr101cli
