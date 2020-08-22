#!/bin/bash
GREEN='\033[1;32m' # define green for SUCCess
NC='\033[0m'

# Compile
javac -cp "junit-4.13.jar;hamcrest-core-1.3.jar;good.jar;." text/TestFlushLeft.java

errorsFound=0
for i in good.jar badA.jar badB.jar badC.jar badD.jar badE.jar badF.jar badG.jar badH.jar badI.jar badJ.jar badK.jar badL.jar badM.jar badN.jar badO.jar; do
    printf "%s: " "$i"
    if [ "$1" ]; then
        quiet=$(java -cp "junit-4.13.jar;hamcrest-core-1.3.jar;$i;." org.junit.runner.JUnitCore text.TestFlushLeft | grep "Tests run")
    else
        java -cp "junit-4.13.jar;hamcrest-core-1.3.jar;$i;." org.junit.runner.JUnitCore text.TestFlushLeft
    fi
    if [ -z "$quiet" ]; then
        printf "No Failures\n"
    else
        printf "${GREEN}FAILED: $quiet\n${NC}"
        (( errorsFound++ ))
    fi
done
if [ "$1" ]; then
    printf "Found errors in %s / 15 JARs\n" "$errorsFound"
fi