package cem.es;

import org.elasticsearch.index.query.QueryBuilder;

import java.util.List;

public class QueryStep {
    private SearchQueryBuilder builder;
    private QueryStep next;
    private List<String> fields;

    public QueryStep(SearchQueryBuilder builder) {
        this(null, builder);
    }

    public QueryStep(List<String> fields, SearchQueryBuilder builder) {
        this.builder = builder;
        this.fields = fields;
    }

    public void setNext(QueryStep next) {
        this.next = next;
    }

    public void getResponse(Parameter parameter) {
        QueryBuilder query = builder.query(parameter);

        System.out.println("query: " + query);
        System.out.println("fields: " + getFields(parameter));
        System.out.println();

        if (next != null) {
            next.getResponse(parameter);
        }
    }

    private List<String> getFields(Parameter parameter) {
        if (fields != null && fields.size() > 0) {
            return fields;
        }

        if (parameter.getFields() != null && parameter.getFields().size() > 0) {
            return parameter.getFields();
        }

        throw new RuntimeException("no fields");
    }
}
