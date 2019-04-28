#!/bin/sh
cd /app
java -Xmx${MAX_MEM} -Djava.security.egd=file:/dev/./urandom -jar ./app.jar --server.port=8080