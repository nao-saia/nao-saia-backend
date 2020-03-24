package br.com.nao.saia.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
public class Category extends EntitySupport {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
