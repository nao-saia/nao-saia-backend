package br.com.nao.saia.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class MerchantDTO implements Serializable {

	private static final long serialVersionUID = 3284286143169387784L;

	private UUID id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateAt;
    @NotNull(message = "Nome fantasia é obrigatório")
    private String fantasyName;
    @NotNull(message = "Nome da empresa é obrigatório")
    private String companyName;
    private String cnpj;
    private AddressDTO address;
    @AssertTrue(message = "É preciso aceitar os termos")
    private boolean acceptTerms;
    private boolean active;
    private String logo;
    @NotEmpty(message = "Categorias é obrigatório")
    private List<String> categories;
    private List<String> ads;
    private String whatsapp;
    private List<String> phones;
    private boolean ifood;
    private boolean uberEats;
    private boolean rappi;
    private boolean ownDelivery;
    private boolean displayAddress;
    private String note;

    @NotNull(message = "ID do usuário é obrigatório")
    private UUID userId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public String getFantasyName() {
        return fantasyName;
    }

    public void setFantasyName(String fantasyName) {
        this.fantasyName = fantasyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public boolean isAcceptTerms() {
        return acceptTerms;
    }

    public void setAcceptTerms(boolean acceptTerms) {
        this.acceptTerms = acceptTerms;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getAds() {
        return ads;
    }

    public void setAds(List<String> ads) {
        this.ads = ads;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public boolean isIfood() {
        return ifood;
    }

    public void setIfood(boolean ifood) {
        this.ifood = ifood;
    }

    public boolean isUberEats() {
        return uberEats;
    }

    public void setUberEats(boolean uberEats) {
        this.uberEats = uberEats;
    }

    public boolean isRappi() {
        return rappi;
    }

    public void setRappi(boolean rappi) {
        this.rappi = rappi;
    }

    public boolean isOwnDelivery() {
        return ownDelivery;
    }

    public void setOwnDelivery(boolean ownDelivery) {
        this.ownDelivery = ownDelivery;
    }

    public boolean isDisplayAddress() {
        return displayAddress;
    }

    public void setDisplayAddress(boolean displayAddress) {
        this.displayAddress = displayAddress;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

}
