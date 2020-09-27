package se325.assignment01.concert.service.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "CONCERTS")
public class Concert {
    @Id
    private Long id;

    private String title;
    @Column(name = "IMAGE_NAME")
    private String imageName;
    @Column(length = 1000)
    private String blurb;

    @OneToMany(mappedBy = "concert", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    private Set<ConcertDate> dates = new HashSet<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "CONCERT_PERFORMER", joinColumns = @JoinColumn(name = "CONCERT_ID"), inverseJoinColumns = @JoinColumn(name = "PERFORMER_ID"))
    private Set<Performer> performers = new HashSet<>();

    public Set<LocalDateTime> getDates() {
        Set<LocalDateTime> dates = new HashSet<>();
        for (ConcertDate concertDate : this.dates) {
            dates.add(concertDate.getDate());
        }
        return dates;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImageName() {
        return imageName;
    }

    public String getBlurb() {
        return blurb;
    }

    public Set<Performer> getPerformers() {
        return performers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Concert concert = (Concert) o;
        return Objects.equals(id, concert.id) &&
                Objects.equals(title, concert.title) &&
                Objects.equals(imageName, concert.imageName) &&
                Objects.equals(blurb, concert.blurb);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, imageName, blurb);
    }

    @Override
    public String toString() {
        return "Concert{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", imageName='" + imageName + '\'' +
                ", blurb='" + blurb + '\'' +
                ", dates=" + dates +
                ", performers=" + performers +
                '}';
    }
}
