package com.cake.manager.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"title", "description"})})
public class Cake {

    private @Id @GeneratedValue Long id;
    private String title;
    private String description;
    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    private @Version @JsonIgnore Long version;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cake cake = (Cake) o;
        return Objects.equals(id, cake.id) && Objects.equals(title, cake.title) && Objects.equals(description, cake.description) && Objects.equals(image, cake.image) && Objects.equals(version, cake.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, image, version);
    }

    @Override
    public String toString() {
        return "Cake{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", version=" + version +
                '}';
    }
}
