package br.com.nao.saia.converter;

import br.com.nao.saia.dto.CityDTO;
import br.com.nao.saia.model.City;
import br.com.nao.saia.service.StateService;
import org.springframework.stereotype.Component;

@Component
public class CityConverter {

    private final StateService stateService;

    public CityConverter(StateService stateService) {
        this.stateService = stateService;
    }

    public City fromDTOToDomain(CityDTO cityDTO) {
        City city = new City();
        city.setCodeIbge(cityDTO.getCodeIbge());
        city.setName(cityDTO.getName());
        city.setState(stateService.findById(cityDTO.getStateId()).block());
        return city;
    }

    public CityDTO fromDomainToDTO(City city) {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setCodeIbge(city.getCodeIbge());
        cityDTO.setName(city.getName());
        cityDTO.setStateId(city.getState().getCodeIbge());
        return cityDTO;
    }

}
