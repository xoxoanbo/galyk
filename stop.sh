#!/bin/bash

ps -ef | grep "spring-boot" | grep $LOGNAME | kill -9 `awk '{print $2}'` 
