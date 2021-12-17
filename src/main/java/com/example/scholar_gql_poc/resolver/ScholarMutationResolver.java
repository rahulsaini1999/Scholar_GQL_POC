package com.example.scholar_gql_poc.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.scholar_gql_poc.model.Scholar;
import com.example.scholar_gql_poc.repository.ScholarRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ScholarMutationResolver implements GraphQLMutationResolver
{
    @Autowired
    private ScholarRepository scholarRepository;

    // to create a scholar dtails in db
    public Scholar createScholar(String id,String name, int age){
            Scholar sc = new Scholar();
            sc.setId(id);
            sc.setName(name);
            sc.setAge(age);
            scholarRepository.save(sc);
            return scholarRepository.findById(id).get();
    }

    // to the update the scholar details
    public Scholar updateScholar(String id,String name, int age)throws
            NotFoundException
    {
        Optional<Scholar> scholar = scholarRepository.findById(id);
        if (scholar.isPresent()) {
            Scholar sc = scholar.get();
            sc.setId(id);
            sc.setName(name);
            sc.setAge(age);
            scholarRepository.save(sc);
            return scholarRepository.findById(id).get();
        }
        throw new NotFoundException("Scholar not found for given id "+id);
    }

    // to delete the scholar details
    public String  deleteScholar(String id) throws NotFoundException
    {
        if(scholarRepository.existsById(id)) {
            scholarRepository.deleteById(id);
            return "Successfully deleted scholar with id "+id;
        }
        throw new NotFoundException("Scholar not found for given id "+id);
    }
}