#!/bin/bash

# start couchdb and create databases
. start_couchdb.sh


HOST='http://localhost:9876/actuator/health'

wait-for-url() {
  echo "Testing $1"
  timeout -s TERM 45 bash -c \
  'while [[ "$(curl -s -o /dev/null -L -w ''%{http_code}'' ${0})" != "200" ]];\
  do echo "Waiting for ${0}" && sleep 2;\
  done' ${1}
  echo "OK!"
  curl -I $1
}
wait-for-url ${HOST}

# team presets by http api
curl http://localhost:9876/teams -H "Content-Type: application/json" -d '{ "name":"JO12-1", "category":"JO12", "description":"Selectie team" }'
curl http://localhost:9876/teams -H "Content-Type: application/json" -d '{ "name":"JO15-2", "category":"JO15" }'
curl http://localhost:9876/teams -H "Content-Type: application/json" -d '{ "name":"JO17-1", "category":"JO17", "description":"Selectie team" }'

# member presets by kafka messages
cat ../preset_data/fakeMembersForTopic.json | ./produceMembers.sh
