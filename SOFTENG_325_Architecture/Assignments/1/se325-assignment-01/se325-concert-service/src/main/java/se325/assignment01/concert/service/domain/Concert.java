package se325.assignment01.concert.service.domain;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
@Entity
public class Concert {
    @Id
    private Long id;

    private String title;
    private String imageName;
    private String blurb;
    // TODO Implement this class.
    public Set<LocalDateTime> getDates() {
        return null;
    }


}
