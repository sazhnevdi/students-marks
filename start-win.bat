call mvn clean
call mvn -DskipTests package
cd target
java -jar students-marks-1.0-shaded.jar