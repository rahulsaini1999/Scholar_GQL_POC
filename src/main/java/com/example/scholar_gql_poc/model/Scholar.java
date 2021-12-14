package com.example.scholar_gql_poc.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Scholar
{
    @Id
    private String id;
    private String name;
    private int age;
}
