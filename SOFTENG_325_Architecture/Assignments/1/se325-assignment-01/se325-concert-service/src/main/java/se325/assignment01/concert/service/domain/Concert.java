package se325.assignment01.concert.service.domain;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name="CONCERTS")
public class Concert {
    @Id
    private Long id;

    private String title;
    @Column(name = "IMAGE_NAME")
    private String imageName;
    @Column(length = 1000)
    private String blurb;

    //@OneToMany(mappedBy = "concert", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @ElementCollection
    @CollectionTable(name="CONCERT_DATES", joinColumns = @JoinColumn(name="CONCERT_ID"))
    @Column(name="DATE")
    private Set<LocalDateTime> dates = new HashSet<>();

    @ManyToMany
    @JoinTable(name="CONCERT_PERFORMER", joinColumns = @JoinColumn(name="CONCERT_ID"), inverseJoinColumns = @JoinColumn(name="PERFORMER_ID"))
    private Set<Performer> performers = new HashSet<>();

    public Set<LocalDateTime> getDates() {
        return null;
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
