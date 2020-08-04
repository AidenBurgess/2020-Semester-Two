#!/bin/bash
GREEN='\033[1;32m' # define green for SUCCess
NC='\033[0m'

# Compile
javac -cp "junit-4.12.jar;hamcrest-core-1.3.jar;." text/TestFlushLeft.java

errorsFound=0
for i in good.jar badA.jar badB.jar badC.jar badD.jar badE.jar badF.jar badG.jar badH.jar badI.jar badJ.jar; do
    printf "$i: "
    if [ $1 ]; then
        quiet=`java -cp "junit-4.12.jar;hamcrest-core-1.3.jar;$i;." org.junit.runner.JUnitCore text.TestFlushLeft | grep "Tests run"`
    else
        java -cp "junit-4.12.jar;hamcrest-core-1.3.jar;$i;." org.junit.runner.JUnitCore text.TestFlushLeft
    fi
    if [ -z "$quiet" ]; then
        printf "No Failures\n"
    else
        printf "${GREEN}FAILED: $quiet\n${NC}"
        let "errorsFound++"
    fi
done
if [ $1 ]; then
    printf "Found errors in $errorsFound / 10 JARs\n"
fi