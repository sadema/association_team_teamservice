## Prerequisites
Kafka must be running and the topics must exist.

## Owner of topics
- public.association.teamservice.team
- public.association.teamservice.player

## Used topics of other services
- public.association.memberservice.member

## Build java service
```shell
mvn clean

# Build the avro data types in the domain module
cd domain
mvn avro:schema
cd ..

mvn install
```

## CouchDB
### run couchdb in docker
```shell script
docker run -d --rm --name team_couchdb -p 5984:5984 -v /opt/couchdb/data --volumes-from team-data couchdb:2.3.1
```

```json
{
  "_id": "_design/team",
  "_rev": "19-7a1070dd139c816f171520d68cb3e6cb",
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
  },
  "language": "javascript"
}
```

```shell
# load the preset data with a shell script from the team-app/scripts directory
# It creates the databases in couchdb and creates the teams and the design documents for the teams frontend
# It produces the MemberSignedUp events on topic public.association.memberservice.member
./loadPresets.sh

```
