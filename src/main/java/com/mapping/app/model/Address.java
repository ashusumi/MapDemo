package com.mapping.app.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
	@SequenceGenerator(name = "address_seq", sequenceName = "address_seq_gen", allocationSize = 100)
	private Long id;

	private String state;

	private String city;

	private String builiding;

	private long roomNo;

}
