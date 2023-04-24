package com.practice.shop.model;

import com.practice.shop.model.schedule.Schedule;
import com.practice.shop.model.user.User;
import com.practice.shop.model.user.UsersCountry;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;


@Getter
@Setter
@Entity
@Table(name = "teachers_descriptions")
@NoArgsConstructor
@AllArgsConstructor
public class TeachersDescription {

    @Id
    @Column(name = "id_teacher", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_teacher")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country")
    private UsersCountry country;
    private Integer experience;
    private String description;

    @Column(name = "photo_ref", length = 400)
    private String photoRef;

    @Column(name = "is_native")
    private Boolean isNative;
    private Double mark;

    private LanguageLevel level;

    @Enumerated(EnumType.STRING)
    private Language language;

    @Column(name = "cost_per_our")
    private BigDecimal defaultPrice;

    @OneToMany(mappedBy = "idTeacher")
    @BatchSize(size = 5)
    private Set<Schedule> schedules = new LinkedHashSet<>();

    @OneToMany(mappedBy = "bannedUser")
    private List<BlackList> blackLists;

    @Override
    public String toString() {
        return "TeachersDescription{" +
                "id=" + id +
                ", country=" + country +
                ", experience=" + experience +
                ", description='" + description + '\'' +
                ", photoRef='" + photoRef + '\'' +
                ", isNative=" + isNative +
                ", defaultPrice=" + defaultPrice +
                '}' ;
    }
}
