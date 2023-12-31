# About
This is a simple java server that reads data from elasticsearch. The project also contains the code necessary to load the data into elastic

## data-loader
contains some scripts that load data from articles / sanction lists into search database

## search-service

spring project that contains the meat of the service. Runs on Java 17 and spring boot 3

### DEV
Needs these ENV variables configured:

* ELASTIC_URI - host + port combo of elastic instance machine.

To run localy, dont forget to do
```
mvn clean compile
```
This will create all the necessary classes so you can run it in your IDE. This is becasue this project uses OpenAPI spec file to generate the server stub interface + some DTOs that are required for function.

You should also have running docker with docker-compose on your machine to launch elasticsearch locally.
You can use dev-infra for this:

```
cd dev-infra
docker-compose up -d
```

## elastic fields explanation
* name - name of the entity
* name_ascii - name of entity in ASCII only
* aliases - array of aliases
* aliases_ascii - array of ASCII aliases
* aliases_count - histogram of aliases. Similar structure to the locations field but with aliases. 
* type - person / organization
* information_source - array of all identified person sources. Can contain values ams / pep / sl
* locations - histogram of locations where this person was. Its basically a json, its structure can be like this: {"Bratislava": 12, "Vienna": 5}. Using this we can determine the most common locations for an entity
* ams_articles - array of links to articles where this person was found
* sl_record - array of SL record IDs
* pep_record - array of PEP record IDs
* all_articles_records_count - total number of articles this person is part of, either as a adverse person or just mentioned there

## swagger
https://app.swaggerhub.com/apis-docs/dominik-horvath/search-service/1.0.0
