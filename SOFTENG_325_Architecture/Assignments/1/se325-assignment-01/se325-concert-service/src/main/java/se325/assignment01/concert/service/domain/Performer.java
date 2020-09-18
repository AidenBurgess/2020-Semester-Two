package se325.assignment01.concert.service.domain;

import se325.assignment01.concert.common.types.Genre;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PERFORMERS")
public class Performer {
    @Id
    private Long id;

    private String name;
    @Column(name = "IMAGE_NAME")
    private String imageName;
    // TODO: Fix this to enum
    private String genre;
    @Column(length = 1000)
    private String blurb;

    @ManyToMany(mappedBy = "performers", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<Concert> concerts = new HashSet<>();

    @Override
    public String toString() {
        String concertsString = "";
        concerts.forEach(concert -> concertsString.concat(concert.getId().toString()));
        return "Performer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageName='" + imageName + '\'' +
                ", genre='" + genre + '\'' +
                ", concertIds=" + concertsString +
                '}';
    }
}
