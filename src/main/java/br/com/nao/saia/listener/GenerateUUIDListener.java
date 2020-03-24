package br.com.nao.saia.listener;

import br.com.nao.saia.model.EntitySupport;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Component
public class GenerateUUIDListener extends AbstractMongoEventListener<EntitySupport> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<EntitySupport> event) {
        EntitySupport entity = event.getSource();
        if (entity.isNew()) {
            entity.setId(UUID.randomUUID());
        }
        if (Objects.isNull(entity.getCreatedAt())) {
            entity.setCreatedAt(LocalDateTime.now());
        }
        if (Objects.isNull(entity.getUpdateAt())) {
            entity.setUpdateAt(LocalDateTime.now());
        }
    }

}
