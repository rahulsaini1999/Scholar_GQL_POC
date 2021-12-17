package com.example.scholar_gql_poc.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.scholar_gql_poc.model.Scholar;
import com.example.scholar_gql_poc.repository.ScholarRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScholarQueryResolver implements GraphQLQueryResolver
{
    private ScholarRepository scholarRepository;

    @Autowired
    public ScholarQueryResolver(ScholarRepository scholarRepository) {
        this.scholarRepository = scholarRepository;
    }
   public List<Scholar> getAllScholars() {
        return scholarRepository.findAll();
    }

    public Scholar getScholarById(String id) throws NotFoundException
    {
        if(scholarRepository.existsById(id)) {
            return scholarRepository.findById(id).get();
        }
        throw new NotFoundException("Scholar not found for given id "+id);
    }
}
