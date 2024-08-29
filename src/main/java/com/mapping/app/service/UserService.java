package com.mapping.app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mapping.app.model.Address;
import com.mapping.app.model.BankAccount;
import com.mapping.app.model.TestMap;
import com.mapping.app.model.User;
import com.mapping.app.model.UserDto;
import com.mapping.app.repo.AddressRepo;
import com.mapping.app.repo.BankAccountRepo;
import com.mapping.app.repo.UserRepo;

import jakarta.annotation.PostConstruct;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepo repo;

	@Autowired
	AddressRepo addressRepo;

	@Autowired
	private BankAccountRepo accountRepo;
	
	 @Value("${excluded.types}")
	    private String excludedTypes;

	 String a="";
	    @PostConstruct
	    public void init() {
	    	a=excludedTypes;
	        System.out.println("Excluded Types: " + excludedTypes);
	        
	    }

	    public List<String> getExcludedTypes() {
	        return Arrays.stream(a.split(","))
	                     .map(String::trim)
	                     .collect(Collectors.toList());
	    }
	
	
	  

	public List<User> getAllUser() {
		return repo.findAll();
	}

	public UserDto getUserById(long id) {
		var usr= repo.findUserWithAddressById(id);
		return UserDto.builder().name(usr.getName()).email(usr.getEmail()).address(usr.getAddress()).account(usr.getBankAccount()).build();
		
	}

	public String addUser(UserDto dto) throws Exception {
		User user = new User();
		System.out.println(excludedTypes);
		// Ensure the address is either fetched or saved
		Address address = dto.getAddress();
		if (address.getId() == 0) { // Assuming id = 0 means not persisted
			address = addressRepo.save(address); // Save the address if it's new
		} else {
			address = addressRepo.findById(address.getId()).orElseThrow(() -> new Exception("Address not found"));
		}
		user.setAddress(address);

		// Set the user fields from DTO
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		

		// Prepare bank accounts
		Set<BankAccount> bankAccounts = new HashSet<>();
		for (BankAccount bankAccount : dto.getAccount()) {
			if (bankAccount.getId() == null) {
				bankAccount.setUser(user); // Set the user to the bank account
				bankAccounts.add(bankAccount);
			} else {
				bankAccounts.add(bankAccount);
			}
		}
		user.setBankAccount(bankAccounts);

		// Save the user with the bank accounts
		repo.save(user);
		log.info("User saved with ID: " + user.getId());

		return "User saved";
	}
	
	
	public void testMap() {
		List<TestMap> list = new ArrayList<TestMap>();
        list.add(new TestMap("ashish", "AB", "thane"));
        list.add(new TestMap("rahul", "BC", "mumbai"));
        list.add(new TestMap("priya", "CD", "pune"));
        list.add(new TestMap("neha", "DE", "nagpur"));
        list.add(new TestMap("rohit", "EF", "nashik"));
        list.add(new TestMap("kiran", "FG", "kolhapur"));
        list.add(new TestMap("anil", "GH", "solapur"));
        list.add(new TestMap("pankaj", "HI", "aurangabad"));
        list.add(new TestMap("suman", "IJ", "latur"));
        list.add(new TestMap("vinay", "JK", "ratnagiri"));
        list.add(new TestMap("vinay", "PQ", "ratnagiri"));

        
        List<String> types = new ArrayList<>();

     // Adding 10 elements representing different types or codes
     types.add("AB");
     types.add("BC");
     types.add("CD");
     types.add("DE");
     types.add("EF");
     types.add("FG");
     types.add("GH");
     types.add("HI");
     types.add("IJ");
     types.add("JK");
     types.add("LM");
     types.add("NO");
     types.add("PQ");	


        Map<String, Object> map=new HashMap<String, Object>();
//        Set<String> set=Arrays.stream(excludedTypes.split(",")).map(String::trim).collect(Collectors.toSet());
     
     for (TestMap testMap : list) {
         String code = testMap.getType(); // Assuming you have a getCode() method in TestMap class
         if (types.contains(code) && !getExcludedTypes().contains(code)) {
             map.put(code, testMap);
         }
     }
     
     System.out.println(map);
		
	}

}
