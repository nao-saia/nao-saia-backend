package br.com.nao.saia.converter;

import br.com.nao.saia.dto.MerchantDTO;
import br.com.nao.saia.model.Merchant;
import org.springframework.stereotype.Component;

@Component
public class MerchantConverter {

    public Merchant fromDTOToDomain(MerchantDTO merchantDTO) {
        Merchant merchant = new Merchant();
        merchant.setId(merchantDTO.getId());
        merchant.setCreatedAt(merchantDTO.getCreatedAt());
        merchant.setUpdateAt(merchantDTO.getUpdateAt());
        merchant.setFantasyName(merchantDTO.getFantasyName());
        merchant.setCompanyName(merchantDTO.getCompanyName());
        merchant.setCnpj(merchantDTO.getCnpj());
        merchant.setAddress(AdressConverter.fromDTOToDomain(merchantDTO.getAddress()));
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
        return merchant;
    }

    public MerchantDTO fromDomainToDTO(Merchant merchant) {
        MerchantDTO merchantDTO = new MerchantDTO();
        merchantDTO.setId(merchant.getId());
        merchantDTO.setCreatedAt(merchant.getCreatedAt());
        merchantDTO.setUpdateAt(merchant.getUpdateAt());
        merchantDTO.setFantasyName(merchant.getFantasyName());
        merchantDTO.setCompanyName(merchant.getCompanyName());
        merchantDTO.setCnpj(merchant.getCnpj());
        merchantDTO.setAddress(AdressConverter.fromDomainToDTO(merchant.getAddress()));
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
        return merchantDTO;
    }
}
