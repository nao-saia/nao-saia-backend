package br.com.nao.saia.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
public class Category extends EntitySupport {

    private String name;
    private String image;
    private boolean highlighted;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }
}
