#!/bin/bash
if [ $# -lt 2 ]; then
  echo "Usage: ./startNextDay.sh tablename jobxml"
  exit 0;
fi
table=$1
jobXml=$2
year=`date -d tomorrow +%Y`
month=`date -d tomorrow +%m`
day=`date -d tomorrow +%d`
currentDir="$(cd `dirname $0`;pwd)"
pname="$table-$year$month$day"
params="-Djob.interval=3 -Djob.year=$year -Djob.month=$month -Djob.day=$day -Djob.pname=$pname"
cd ..
cmd="python bin/datax.py -p \"$params\" $jobXml"
eval $cmd

