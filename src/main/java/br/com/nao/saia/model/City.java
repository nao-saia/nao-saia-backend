package br.com.nao.saia.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cities")
public class City {

    @Id
    private Integer codeIbge;

    private String name;

    private State state;

    public Integer getCodeIbge() {
        return codeIbge;
    }

    public void setCodeIbge(Integer codeIbge) {
        this.codeIbge = codeIbge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
