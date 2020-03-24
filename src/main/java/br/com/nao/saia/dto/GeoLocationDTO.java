package br.com.nao.saia.dto;

public class GeoLocationDTO {

    private Double latitude;
    private Double longitude;

    public GeoLocationDTO() {
    }

    public GeoLocationDTO(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
