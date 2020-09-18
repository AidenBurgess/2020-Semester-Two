package se325.assignment01.concert.service.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

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

    @OneToMany(mappedBy = "concert", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.EAGER)
    //@ElementCollection
    //@CollectionTable(name="CONCERT_DATES", joinColumns = @JoinColumn(name="CONCERT_ID"))
    //@Column(name="DATE")
    private Set<ConcertDate> dates = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
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
