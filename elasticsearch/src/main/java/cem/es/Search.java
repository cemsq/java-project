package cem.es;

import com.google.common.collect.Lists;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.node.NodeBuilder;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.*;

public class Search {

    private static final QueryStep gtin_pzn_step;
    private static final QueryStep hibc_step;
    private static final QueryStep byText;
    static {
        QueryStep step3;
        QueryStep step2;
        QueryStep step1;

        step1 = new QueryStep(null);
        step2 = new QueryStep(null);
        step3 = new QueryStep(null);
        step1.setNext(step2);
        step2.setNext(step3);
        byText = step1;

        step1 = new QueryStep(fields("id", "type"), (parameter) -> {
            TermQueryBuilder idQuery = termQuery("id", parameter.identification);
            TermQueryBuilder typeQuery = termQuery("type", parameter.type);
            return boolQuery().must(idQuery).must(typeQuery);
        });
        step2 = new QueryStep(fields("id", "type"), parameter -> {
            TermQueryBuilder idQuery = termQuery("id", parameter.identification);
            TermQueryBuilder typeQuery = termQuery("type", parameter.type);
            return boolQuery().must(idQuery).must(typeQuery);
        });
        step3 = new QueryStep(parameter -> null);
        step1.setNext(step2);
        step2.setNext(step3);
        gtin_pzn_step = step1;


        step1 = new QueryStep(fields("id", "type"), parameter -> {
            MatchQueryBuilder idQuery = matchQuery("id", parameter.identification);
            TermQueryBuilder typeQuery = termQuery("type", parameter.type);
            return boolQuery().must(idQuery).must(typeQuery);
        });
        step2 = new QueryStep(fields("id", "itemNumber"), parameter ->
                multiMatchQuery(parameter.identification, "id", "itemNumber"));
        step1.setNext(step2);
        hibc_step = step1;
    }

    public void search(Integer categoryId, String text) {
        Parameter parameter = new Parameter();
        parameter.text = text;

        List<String> fields = fields("f1", "f2", "f3");
        parameter.setFields(fields);

        byText.getResponse(parameter);
    }

    public void search(String id, String type) {
        Parameter parameter = new Parameter();
        parameter.identification = id;
        parameter.type = type;

        hibc_step.getResponse(parameter);
    }

    public static List<String> fields(String ... fields) {
        return Lists.newArrayList(fields);
    }
}
