package br.com.nao.saia.converter;

import br.com.nao.saia.dto.ContributorDTO;
import br.com.nao.saia.model.Contributor;

import java.time.LocalDateTime;
import java.util.Optional;

public final class ContributorConverter {

    private ContributorConverter() {
    }

    public static Contributor fromDTOToDomain(ContributorDTO contributorDTO) {
        Contributor contributor = new Contributor();
        contributor.setId(contributorDTO.getId());
        contributor.setName(contributorDTO.getName());
        contributor.setDescription(contributorDTO.getDescription());
        contributor.setInstagram(contributorDTO.getInstagram());
        contributor.setFacebook(contributorDTO.getFacebook());
        contributor.setLinkedin(contributorDTO.getLinkedin());
        contributor.setTwitter(contributorDTO.getTwitter());
        contributor.setGithub(contributorDTO.getGithub());
        contributor.setImage(contributorDTO.getImage());
        return contributor;
    }

    public static ContributorDTO fromDomainToDTO(Contributor contributor) {
        ContributorDTO contributorDTO = new ContributorDTO();
        contributorDTO.setId(contributor.getId());
        contributorDTO.setName(contributor.getName());
        contributorDTO.setDescription(contributor.getDescription());
        contributorDTO.setInstagram(contributor.getInstagram());
        contributorDTO.setFacebook(contributor.getFacebook());
        contributorDTO.setLinkedin(contributor.getLinkedin());
        contributorDTO.setTwitter(contributor.getTwitter());
        contributorDTO.setGithub(contributor.getGithub());
        contributorDTO.setImage(contributor.getImage());
        contributorDTO.setCreatedAt(contributor.getCreatedAt());
        contributorDTO.setUpdateAt(contributor.getUpdateAt());
        return contributorDTO;
    }

    public static Contributor update(Contributor oldContributor, ContributorDTO newContributor) {
        oldContributor.setUpdateAt(LocalDateTime.now());

        Optional.ofNullable(newContributor.getName()).ifPresent(oldContributor::setName);
        Optional.ofNullable(newContributor.getDescription()).ifPresent(oldContributor::setDescription);
        Optional.ofNullable(newContributor.getInstagram()).ifPresent(oldContributor::setInstagram);
        Optional.ofNullable(newContributor.getFacebook()).ifPresent(oldContributor::setFacebook);
        Optional.ofNullable(newContributor.getLinkedin()).ifPresent(oldContributor::setLinkedin);
        Optional.ofNullable(newContributor.getTwitter()).ifPresent(oldContributor::setTwitter);
        Optional.ofNullable(newContributor.getGithub()).ifPresent(oldContributor::setGithub);
        Optional.ofNullable(newContributor.getImage()).ifPresent(oldContributor::setImage);
        return oldContributor;
    }

}
