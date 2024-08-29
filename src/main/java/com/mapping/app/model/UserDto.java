package com.mapping.app.model;


import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {

	private String name;
	
	private String email;
	
	private Address address;
	
	private Set<BankAccount> account;
}
