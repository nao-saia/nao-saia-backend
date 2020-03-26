package br.com.nao.saia.converter;

import br.com.nao.saia.dto.MerchantDTO;
import br.com.nao.saia.model.Address;
import br.com.nao.saia.model.Merchant;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public final class MerchantConverter {

    public MerchantConverter() {
    }

    public static Merchant fromDTOToDomain(MerchantDTO merchantDTO) {
        Merchant merchant = new Merchant();
        merchant.setId(merchantDTO.getId());
        merchant.setCreatedAt(merchantDTO.getCreatedAt());
        merchant.setUpdateAt(merchantDTO.getUpdateAt());
        merchant.setFantasyName(merchantDTO.getFantasyName());
        merchant.setCompanyName(merchantDTO.getCompanyName());
        merchant.setCnpj(merchantDTO.getCnpj());
        merchant.setAddress(AddressConverter.fromDTOToDomain(merchantDTO.getAddress()));
        merchant.setAcceptTerms(merchantDTO.isAcceptTerms());
        merchant.setActive(merchantDTO.isActive());
        merchant.setLogo(merchantDTO.getLogo());
        merchant.setCategories(merchantDTO.getCategories());
        merchant.setAds(merchantDTO.getAds());
        merchant.setWhatsapp(merchantDTO.getWhatsapp());
        merchant.setPhones(merchantDTO.getPhones());
        merchant.setIfood(merchantDTO.isIfood());
        merchant.setUberEats(merchantDTO.isUberEats());
        merchant.setRappi(merchantDTO.isRappi());
        merchant.setOwnDelivery(merchantDTO.isOwnDelivery());
        merchant.setDisplayAddress(merchantDTO.isDisplayAddress());
        merchant.setNote(merchantDTO.getNote());
        merchant.setUserId(merchantDTO.getUserId());
        return merchant;
    }

    public static MerchantDTO fromDomainToDTO(Merchant merchant) {
        MerchantDTO merchantDTO = new MerchantDTO();
        merchantDTO.setId(merchant.getId());
        merchantDTO.setCreatedAt(merchant.getCreatedAt());
        merchantDTO.setUpdateAt(merchant.getUpdateAt());
        merchantDTO.setFantasyName(merchant.getFantasyName());
        merchantDTO.setCompanyName(merchant.getCompanyName());
        merchantDTO.setCnpj(merchant.getCnpj());
        merchantDTO.setAddress(AddressConverter.fromDomainToDTO(merchant.getAddress()));
        merchantDTO.setAcceptTerms(merchant.isAcceptTerms());
        merchantDTO.setActive(merchant.isActive());
        merchantDTO.setLogo(merchant.getLogo());
        merchantDTO.setCategories(merchant.getCategories());
        merchantDTO.setAds(merchant.getAds());
        merchantDTO.setWhatsapp(merchant.getWhatsapp());
        merchantDTO.setPhones(merchant.getPhones());
        merchantDTO.setIfood(merchant.isIfood());
        merchantDTO.setUberEats(merchant.isUberEats());
        merchantDTO.setRappi(merchant.isRappi());
        merchantDTO.setOwnDelivery(merchant.isOwnDelivery());
        merchantDTO.setDisplayAddress(merchant.isDisplayAddress());
        merchantDTO.setNote(merchant.getNote());
        merchantDTO.setUserId(merchant.getUserId());
        return merchantDTO;
    }

    public static Merchant update(Merchant oldMerchant, MerchantDTO newMerchant) {
        oldMerchant.setUpdateAt(LocalDateTime.now());
        Optional.ofNullable(newMerchant.getFantasyName()).ifPresent(oldMerchant::setFantasyName);
        Optional.ofNullable(newMerchant.getCompanyName()).ifPresent(oldMerchant::setCompanyName);
        Optional.ofNullable(newMerchant.getCnpj()).ifPresent(oldMerchant::setCnpj);

        if (Objects.nonNull(newMerchant.getAddress())) {
            Address addressUpdated = AddressConverter.update(oldMerchant.getAddress(), newMerchant.getAddress());
            Optional.ofNullable(addressUpdated).ifPresent(oldMerchant::setAddress);
        }

        Optional.of(newMerchant.isAcceptTerms()).ifPresent(oldMerchant::setAcceptTerms);
        Optional.of(newMerchant.isActive()).ifPresent(oldMerchant::setActive);
        Optional.ofNullable(newMerchant.getLogo()).ifPresent(oldMerchant::setLogo);
        Optional.ofNullable(newMerchant.getCategories()).ifPresent(oldMerchant::setCategories);
        Optional.ofNullable(newMerchant.getAds()).ifPresent(oldMerchant::setAds);
        Optional.ofNullable(newMerchant.getWhatsapp()).ifPresent(oldMerchant::setWhatsapp);
        Optional.ofNullable(newMerchant.getPhones()).ifPresent(oldMerchant::setPhones);
        Optional.of(newMerchant.isIfood()).ifPresent(oldMerchant::setIfood);
        Optional.of(newMerchant.isUberEats()).ifPresent(oldMerchant::setUberEats);
        Optional.of(newMerchant.isRappi()).ifPresent(oldMerchant::setRappi);
        Optional.of(newMerchant.isOwnDelivery()).ifPresent(oldMerchant::setOwnDelivery);
        Optional.of(newMerchant.isDisplayAddress()).ifPresent(oldMerchant::setDisplayAddress);
        Optional.ofNullable(newMerchant.getNote()).ifPresent(oldMerchant::setNote);
        Optional.ofNullable(newMerchant.getUserId()).ifPresent(oldMerchant::setUserId);
        return oldMerchant;
    }
}
