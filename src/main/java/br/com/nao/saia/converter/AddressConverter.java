package br.com.nao.saia.converter;

import br.com.nao.saia.dto.AddressDTO;
import br.com.nao.saia.dto.GeoLocationDTO;
import br.com.nao.saia.model.Address;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

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
        address.setAdditionalInfo(addressDTO.getAdditionalInfo());
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
        addressDTO.setAdditionalInfo(address.getAdditionalInfo());
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

	public static Address update(Address oldAddress, AddressDTO newAddress) {
		GeoLocationDTO location = newAddress.getLocation();
		if (Objects.nonNull(location) && Objects.nonNull(location.getLatitude()) && Objects.nonNull(location.getLongitude())) {
			GeoJsonPoint geoJsonPoint = new GeoJsonPoint(location.getLatitude(), location.getLongitude());
			Optional.of(geoJsonPoint).ifPresent(oldAddress::setLocation);
		}
		Optional.ofNullable(newAddress.getStreet()).ifPresent(oldAddress::setStreet);
		Optional.ofNullable(newAddress.getDistrict()).ifPresent(oldAddress::setDistrict);
		Optional.ofNullable(newAddress.getZipcode()).ifPresent(oldAddress::setZipcode);
		Optional.ofNullable(newAddress.getCity()).ifPresent(oldAddress::setCity);
		Optional.ofNullable(newAddress.getState()).ifPresent(oldAddress::setState);
		return oldAddress;
	}

}
