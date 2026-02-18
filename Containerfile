FROM debian:stable

ARG JAVA_VERSION=21

ARG CQL_ON_OMOP_VERSION=1.0-SNAPSHOT

ARG SOURCE_DB_HOST
ARG SOURCE_DB_PORT=5432
ARG SOURCE_DB_USER=postgres
ARG SOURCE_DB_PASSWORD # TODO
ARG SOURCE_DB_DATABASE=ohdsi
ARG SOURCE_DB_SCHEMA=cds_cdm

ARG TARGET_DB_HOST
ARG TARGET_DB_PORT=5432
ARG TARGET_DB_USER=postgres
ARG TARGET_DB_PASSWORD # TODO
ARG TARGET_DB_DATABASE=ohdsi
ARG TARGET_DB_SCHEMA=cds_cdm

RUN DEBIAN_FRONTEND=noninteractive apt-get --assume-yes update     \
    && DEBIAN_FRONTEND=noninteractive apt-get --assume-yes install \
         openjdk-${JAVA_VERSION}-jre-headless                      \
    && apt-get clean

WORKDIR /app

RUN mkdir -p cql
COPY target/cql-on-omop-${CQL_ON_OMOP_VERSION}.jar .

ENV CQL_ON_OMOP_VERSION=${CQL_ON_OMOP_VERSION}

ENV SOURCE_DB_HOST=${SOURCE_DB_HOST}
ENV SOURCE_DB_PORT=${SOURCE_DB_PORT}
ENV SOURCE_DB_USER=${SOURCE_DB_USER}
ENV CQL_ON_OMOP_DATABASE_PASSWORD=${SOURCE_DB_PASSWORD}
ENV SOURCE_DB_DATABASE=${SOURCE_DB_DATABASE}
ENV SOURCE_DB_SCHEMA=${SOURCE_DB_SCHEMA}

ENV TARGET_DB_HOST=${TARGET_DB_HOST}
ENV TARGET_DB_PORT=${TARGET_DB_PORT}
ENV TARGET_DB_USER=${TARGET_DB_USER}
#ENV CQL_ON_OMOP_DATABASE_PASSWORD=${TARGET_DB_PASSWORD}
ENV TARGET_DB_DATABASE=${TARGET_DB_DATABASE}
ENV TARGET_DB_SCHEMA=${TARGET_DB_SCHEMA}

ENV CQL_CONTEXT="[Person]"

CMD java -Xmx24000000000                          \
      -jar cql-on-omop-${CQL_ON_OMOP_VERSION}.jar \
      batch                                       \
        --host="${SOURCE_DB_HOST}"                \
        --port="${SOURCE_DB_PORT}"                \
        --user="${SOURCE_DB_USER}"                \
        --database="${SOURCE_DB_DATABASE}"        \
        --schema="${SOURCE_DB_SCHEMA}"            \
        --result-name=".*result.*"                \
        --context-value "${CQL_CONTEXT}"          \
        -I cql/                                   \
        main                                      \
        dbwrite                                   \
          --host="${TARGET_DB_HOST}"              \
          --port="${TARGET_DB_PORT}"              \
          --user="${TARGET_DB_USER}"              \
          --database="${TARGET_DB_DATABASE}"      \
          --schema="${TARGET_DB_SCHEMA}"
