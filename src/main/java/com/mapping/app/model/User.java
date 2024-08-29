package com.mapping.app.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "m_user")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_user_seq")
    @SequenceGenerator(name = "m_user_seq", sequenceName = "m_user_seq_gen", allocationSize = 100)
    private Long id;
    
    private String name;
    
    private String email;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    @JsonBackReference
    private Address address;
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user",orphanRemoval = true)
    @JsonManagedReference
    private Set<BankAccount> bankAccount;
    
}
