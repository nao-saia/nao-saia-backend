package br.com.nao.saia.converter;

import br.com.nao.saia.dto.AddressDTO;
import br.com.nao.saia.dto.GeoLocationDTO;
import br.com.nao.saia.model.Address;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

public final class AddressConverter {

    private AddressConverter() {
    }

    public static Address fromDTOToDomain(AddressDTO addressDTO) {
        Address address = new Address();
        address.setLocation(new GeoJsonPoint(addressDTO.getLocation().getLatitude(), addressDTO.getLocation().getLongitude()));
        address.setStreet(addressDTO.getStreet());
        address.setDistrict(addressDTO.getDistrict());
        address.setZipcode(addressDTO.getZipcode());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        return address;
    }

    public static AddressDTO fromDomainToDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setLocation(new GeoLocationDTO(address.getLocation().getX(), address.getLocation().getY()));
        addressDTO.setStreet(address.getStreet());
        addressDTO.setDistrict(address.getDistrict());
        addressDTO.setZipcode(address.getZipcode());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        return addressDTO;
    }

}
