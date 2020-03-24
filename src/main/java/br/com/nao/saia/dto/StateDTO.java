package br.com.nao.saia.dto;

public class StateDTO {

    private Integer codeIbge;

    private String name;

    private String uf;

    private String country;

    public StateDTO() {
    }

    public StateDTO(Integer codeIbge, String name, String uf, String country) {
        this.codeIbge = codeIbge;
        this.name = name;
        this.uf = uf;
        this.country = country;
    }

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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
