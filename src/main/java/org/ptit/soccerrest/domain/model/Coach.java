package org.ptit.soccerrest.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "coaches")
@NoArgsConstructor
@Getter
@Setter
public class Coach extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String name;

    private Integer age;

    @Column(length = 100)
    private String nationality;

    @Column(name = "license_level", length = 50)
    private String licenseLevel;

    @Column(name = "experience_years")
    private Integer experienceYears;

    @Column(name = "club_name", length = 100)
    private String clubName;

    @Column(name = "preferred_formation", length = 20)
    private String preferredFormation;

    private Boolean active = Boolean.TRUE;

}
