package br.com.nao.saia.dto;

public class CityDTO {

    private Integer codeIbge;

    private String name;

    private Integer stateId;

    public CityDTO() {
    }

    public CityDTO(Integer codeIbge, String name, Integer stateId) {
        this.codeIbge = codeIbge;
        this.name = name;
        this.stateId = stateId;
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

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }
}
