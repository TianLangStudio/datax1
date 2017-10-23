#!/bin/bash
if [ $# -lt 1 ]; then
   echo "Usage: ./stopPrevDay.sh tablename"
   exit 0
fi
table=$1
year=`date -d yesterday +%Y`
month=`date -d yesterday +%m`
day=`date -d yesterday +%d`
pname="$table-$year$month$day"
pids=`ps -ef|grep $pname|grep -v "$0"|grep -v "grep"|awk '{print $2}'`

for pid in $pids
 do 
   kill $pid
   echo "killed $pid"
done



