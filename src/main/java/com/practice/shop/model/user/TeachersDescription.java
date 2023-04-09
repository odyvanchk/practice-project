package com.practice.shop.model.user;

import com.practice.shop.model.Language;
import com.practice.shop.model.Schedule;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "teachers_descriptions")
@NoArgsConstructor
@AllArgsConstructor
public class TeachersDescription {

    @Id
    @Column(name = "id_user", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_user")
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

    @Column(name = "default_price")
    private BigDecimal defaultPrice;

    @Column(name = "default_student_count")
    private Integer defaultStudentCount;

    private Language language;

    @OneToMany(mappedBy = "idTeacher")
    @BatchSize(size = 5)
    private Set<Schedule> schedules = new LinkedHashSet<>();


    @Override
    public String toString() {
        return "TeachersDescription{" +
                "id=" + id +
                ", country=" + country +
                ", experience=" + experience +
                ", description='" + description + '\'' +
                ", photoRef='" + photoRef + '\'' +
                ", isNative=" + isNative +
                ", mark=" + mark +
                ", defaultPrice=" + defaultPrice +
                ", defaultStudentCount=" + defaultStudentCount +
                '}' ;
    }
}
