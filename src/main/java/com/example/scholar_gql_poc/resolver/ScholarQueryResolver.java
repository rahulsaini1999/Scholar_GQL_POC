package com.example.scholar_gql_poc.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.scholar_gql_poc.model.Scholar;
import com.example.scholar_gql_poc.repository.ScholarRepository;
import graphql.relay.Connection;
import graphql.relay.SimpleListConnection;
import graphql.schema.DataFetchingEnvironment;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScholarQueryResolver implements GraphQLQueryResolver
{
    @Autowired
    private ScholarRepository scholarRepository;

   public List<Scholar> getAllScholars() {
        return scholarRepository.findAll();
    }

    // to fetch the scholrs detail for a single scholar
    public Scholar getScholarById(String id) throws NotFoundException
    {
        if(scholarRepository.existsById(id)) {
            return scholarRepository.findById(id).get();
        }
        throw new NotFoundException("Scholar not found for given id "+id);
    }

    //to get the total scholar count
    public long countScholar() {
        return scholarRepository.count();
    }


    public Connection<Scholar> scholarForward (int first, int after, DataFetchingEnvironment env)
    {
        List<Scholar> scholar = scholarRepository.findAll();
        return new SimpleListConnection<>(scholar).get(env);
    }

    public Connection<Scholar> scholarBackWard(int last, String before, DataFetchingEnvironment env) {
        List<Scholar> scholar = scholarRepository.findAll();
        return new SimpleListConnection<>(scholar).get(env);
    }

}
