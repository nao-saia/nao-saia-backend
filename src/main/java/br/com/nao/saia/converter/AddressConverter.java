package br.com.nao.saia.converter;

import br.com.nao.saia.dto.AddressDTO;
import br.com.nao.saia.dto.GeoLocationDTO;
import br.com.nao.saia.model.Address;

import java.util.Map;

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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static AddressDTO fromMapToAddressDTO(final Map<String, Object> address) {
		String street = (String) address.get("logradouro");
		String zipCode = (String) address.get("cep");
		String latitude = (String) address.get("latitude");
		String longitude = (String) address.get("longitude");
		String district = (String) address.get("bairro");
		Map<String, Object> cityMap = (Map) address.get("cidade");

		String city = null;
		if (cityMap != null) {
			city = (String) cityMap.get("nome");
		}
		String state = null;
		Map<String, String> stateMap = (Map) address.get("estado");
		if ( stateMap!= null) {
			state = stateMap.get("sigla");
		}

		GeoLocationDTO geoLocation = new GeoLocationDTO();
		geoLocation.setLatitude(Double.valueOf(latitude));
		geoLocation.setLongitude(Double.valueOf(longitude));

		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setStreet(street);
		addressDTO.setZipcode(zipCode);
		addressDTO.setState(state);
		addressDTO.setCity(city);
		addressDTO.setDistrict(district);
		addressDTO.setLocation(geoLocation);

		return addressDTO;
	}

}
