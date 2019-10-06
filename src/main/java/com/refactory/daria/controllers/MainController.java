package com.refactory.daria.controllers;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    /*
    private GraphQL graphQL;
    private GraphQLUtility graphQlUtility;

    @Autowired
    public MainController(GraphQLUtility graphQlUtility) throws IOException {
        this.graphQlUtility = graphQlUtility;
        graphQL = graphQlUtility.createGraphQlObject();
    }

    @PostMapping(value = "/graphql")
    public ResponseEntity query(@RequestBody Map<String, Object> query) {

        String queryStr = null;
        if (query.get("query") != null) {
            queryStr = String.valueOf(query.get("query"));
        } else if (query.get("mutation") != null) {
            queryStr = String.valueOf(query.get("mutation"));
        }

        ExecutionResult executionResult = graphQL.execute(
                ExecutionInput.newExecutionInput()
                .query(queryStr)
                .variables((Map<String, Object>)query.get("variables"))
                .build()
        );

        final List<GraphQLError> errors = executionResult.getErrors();
        System.out.println("errors: " + errors);
        Map<String, Object> response = new HashMap<>();
        response.put("data", executionResult.getData());
        return ResponseEntity.ok(response);

    }
     */

}
