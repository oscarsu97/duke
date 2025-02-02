 @ECHO OFF
   
   REM create bin directory if it doesn't exist
   if not exist duke\bin mkdir duke\bin
   
   REM delete output from previous run
   del ACTUAL.TXT
   
   REM compile the code into the bin folder
   javac  -cp duke\src -Xlint:none -d duke\bin duke\src\main\java\duke.java
   IF ERRORLEVEL 1 (
       echo ********** BUILD FAILURE **********
       exit /b 1
   )
   REM no error here, errorlevel == 0
   
   REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
   java -classpath duke\bin duke < input.txt > ACTUAL.TXT
   
   REM compare the output to the expected output
   FC ACTUAL.TXT EXPECTED.TXT
   ```
   
   `runtest.sh`:
   ```shell
   #!/usr/bin/env bash
   
   # create bin directory if it doesn't exist
   if [ ! -d "duke/bin" ]
   then
       mkdir duke/bin
   fi
   
   # delete output from previous run
   if [ -e "./ACTUAL.TXT" ]
   then
       rm ACTUAL.TXT
   fi
	
   # compile the code into the bin folder, terminates if error occurred
   if ! javac -cp duke/src -Xlint:none -d duke/bin duke/src/main/java/duke.java
   then
       echo "********** BUILD FAILURE **********"
       exit 1
   fi
   
   # run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
   java -classpath duke/bin duke < input.txt > ACTUAL.TXT
   
   # compare the output to the expected output
   diff ACTUAL.TXT EXPECTED.TXT
   if [ $? -eq 0 ]
   then
       echo "Test result: PASSED"
       exit 0
   else
       echo "Test result: FAILED"
       exit 1
   fi