#!/bin/sh
eval "mvn clean && mvn -DskipTests install && cd target && java -jar students-marks-1.0-shaded.jar"