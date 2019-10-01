package com.refactory.daria.controllers;

import com.refactory.daria.graphql.GraphQLUtility;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    private GraphQL graphQL;
    private GraphQLUtility graphQlUtility;

    @Autowired
    public MainController(GraphQLUtility graphQlUtility) throws IOException {
        this.graphQlUtility = graphQlUtility;
        graphQL = graphQlUtility.createGraphQlObject();
    }

    @PostMapping(value = "/graphql")
    public ResponseEntity query(@RequestBody Map<String, Object> query) {
        final String requestString = (String)query.get("query");
        ExecutionResult executionResult = graphQL.execute(requestString);
        final List<GraphQLError> errors = executionResult.getErrors();
        System.out.println("errors: " + errors);
        Map<String, Object> response = new HashMap<>();
        response.put("data", executionResult.getData());
        ResponseEntity resp = ResponseEntity.ok(response);
        return resp;
    }

}
