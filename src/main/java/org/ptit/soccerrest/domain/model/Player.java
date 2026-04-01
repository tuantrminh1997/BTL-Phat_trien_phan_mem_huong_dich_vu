package org.ptit.soccerrest.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "players")
@NoArgsConstructor
@Getter
@Setter
public class Player extends BaseEntity {

	@Column(nullable = false, length = 100)
	private String name;

	private Integer age;

	@Column(length = 100)
	private String nationality;

	@Column(length = 50)
	private String position;

	@Column(name = "jersey_number")
	private Integer jerseyNumber;

	@Column(name = "club_name", length = 100)
	private String clubName;

	@Column(name = "preferred_foot", length = 20)
	private String preferredFoot;

	@Column(name = "market_value", precision = 15, scale = 2)
	private BigDecimal marketValue;

	private Boolean active = Boolean.TRUE;

}
