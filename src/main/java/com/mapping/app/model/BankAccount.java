package com.mapping.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "bank_account")
public class BankAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_acc_seq")
	@SequenceGenerator(name = "bank_acc_seq", sequenceName = "bank_acc_gen", allocationSize = 100)
	private Long id;

	private String accountNo;

	private String accountType;

	private long balance;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;

}
