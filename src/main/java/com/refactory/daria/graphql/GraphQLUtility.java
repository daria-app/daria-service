package com.refactory.daria.graphql;

import com.refactory.daria.graphql.fetchers.*;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

import static graphql.GraphQL.newGraphQL;
import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

@Component
public class GraphQLUtility {

    @Value("classpath:schema.graphqls")
    private Resource schemaResource;
    private GraphQL graphQL;
    private UserDataFetcher userDataFetcher;
    private AllTracksDataFetcher allTracksDataFetcher;
    private SubscribedTracksDataFetcher subscribedTracksDataFetcher;
    private ContributedTracksDataFetcher contributedTracksDataFetcher;
    private SubscribersDataFetcher subscribersDataFetcher;
    private ContributorsDataFetcher contributorsDataFetcher;

    @Autowired
    GraphQLUtility(UserDataFetcher userDataFetcher,
                   AllTracksDataFetcher allTracksDataFetcher,
                   SubscribedTracksDataFetcher subscribedTracksDataFetcher,
                   ContributedTracksDataFetcher contributedTracksDataFetcher,
                   SubscribersDataFetcher subscribersDataFetcher,
                   ContributorsDataFetcher contributorsDataFetcher) throws IOException {
        this.userDataFetcher = userDataFetcher;
        this.allTracksDataFetcher = allTracksDataFetcher;
        this.subscribedTracksDataFetcher = subscribedTracksDataFetcher;
        this.contributedTracksDataFetcher = contributedTracksDataFetcher;
        this.subscribersDataFetcher = subscribersDataFetcher;
        this.contributorsDataFetcher = contributorsDataFetcher;
    }

    @PostConstruct
    public GraphQL createGraphQlObject() throws IOException {
        File schemas = schemaResource.getFile();
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemas);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        return  newGraphQL(schema).build();
    }

    public RuntimeWiring buildRuntimeWiring() {
        return newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("user", userDataFetcher)
                        .dataFetcher("tracks", allTracksDataFetcher))
                .type("User", typeWiring -> typeWiring
                        .dataFetcher("subscribedTracks", subscribedTracksDataFetcher)
                        .dataFetcher("contributedTracks", contributedTracksDataFetcher))
                .type("Track", typeWiring -> typeWiring
                        .dataFetcher("subscribers", subscribersDataFetcher)
                        .dataFetcher("contributors", contributorsDataFetcher))
                .build();
    }
}
