#!/bin/bash

CONTAINER_ID=$(docker ps | grep team_couchdb | awk '{print $1}')
echo "Container id: ${CONTAINER_ID}"
if [ -z "${CONTAINER_ID}" ];
then
  docker run -d --rm --name team_couchdb -p 5984:5984 team_couchdb:1.0.0
else
  docker restart team_couchdb
fi
sleep 5
curl http://localhost:5984

curl -X PUT http://localhost:5984/_users
curl -X PUT http://localhost:5984/_replicator
curl -X PUT http://localhost:5984/teams

cat <<'EOF' | curl -v http://localhost:5984/teams -H "Content-type: application/json" -d "$(</dev/stdin)"
{
    "_id": "_design/team",
    "views": {
        "new-players": {
            "map": "function (doc) {\n  if (doc.type === \"PLAYER\" && doc.team_reference === null) {\n    emit(doc._id, doc);\n  }\n}"
          },
        "team-players": {
            "map": "function (doc) {\n  if (doc.team_reference) {\n    emit(doc.team_reference, doc);\n  }\n}"
          },
        "all-players": {
            "map": "function (doc) {\n  if (doc.type === 'PLAYER') {\n    emit(doc._id, doc);\n  }\n}"
          },
        "all-teams-and-teamplayers": {
            "map": "function (doc) {\n  if (doc.type === \"TEAM\") {\n    emit(doc._id, doc);\n  }\n  else {\n    if (doc.type === \"PLAYER\" && doc.team_reference !== null) {\n      emit(doc.team_reference, doc);\n    }\n  }\n}"
          },
        "all-teams": {
            "map": "function (doc) {\n  if (doc.type === 'TEAM') {\n    emit(doc._id, doc);\n  }\n}"
          }
    }
}
EOF

nohup java -jar -Dspring.profiles.active=devlocal -Dschema.registry.url=http://localhost:8081 ./team-app/target/team-app-*.jar &

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

curl http://localhost:9876/teams -H "Content-Type: application/json" -d '{ "name":"JO12-1", "category":"JO12", "description":"Selectie team" }'
curl http://localhost:9876/teams -H "Content-Type: application/json" -d '{ "name":"JO15-2", "category":"JO15" }'
curl http://localhost:9876/teams -H "Content-Type: application/json" -d '{ "name":"JO17-1", "category":"JO17", "description":"Selectie team" }'

