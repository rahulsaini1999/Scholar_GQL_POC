package com.example.scholar_gql_poc.repository;

import com.example.scholar_gql_poc.model.Scholar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScholarRepository extends JpaRepository<Scholar, String>
{
}

