package se325.assignment01.concert.service.domain;

import se325.assignment01.concert.common.types.Genre;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "PERFORMERS")
public class Performer {
    @Id
    private Long id;

    private String name;
    @Column(name = "IMAGE_NAME")
    private String imageName;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Column(length = 1000)
    private String blurb;

    @ManyToMany(mappedBy = "performers", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<Concert> concerts = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Performer performer = (Performer) o;
        return Objects.equals(id, performer.id) &&
                Objects.equals(name, performer.name) &&
                Objects.equals(imageName, performer.imageName) &&
                genre == performer.genre &&
                Objects.equals(blurb, performer.blurb);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, imageName, genre, blurb);
    }

    @Override
    public String toString() {
        String concertsString = "";
        for (Concert concert : concerts) {
            concertsString = concertsString.concat(concert.getId().toString());
        }
        return "Performer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageName='" + imageName + '\'' +
                ", genre='" + genre + '\'' +
                ", concertIds=" + concertsString +
                '}';
    }
}
