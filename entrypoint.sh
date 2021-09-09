#!/bin/sh

CMD="java $JAVA_OPTS -cp .:${JDBC_JAR_PATH} OracleJdbcTester"
if [ "x$RETRY_INTERVAL" != "x" ]; then
   until /bin/sh -c "$CMD"
   do 
      sleep $RETRY_INTERVAL
   done
else
   /bin/sh -c "$CMD"
fi