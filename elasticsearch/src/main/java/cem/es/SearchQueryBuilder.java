package cem.es;


import org.elasticsearch.index.query.QueryBuilder;

public interface SearchQueryBuilder {

    QueryBuilder query(Parameter parameter);
}
