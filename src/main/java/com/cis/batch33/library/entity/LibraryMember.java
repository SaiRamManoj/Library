package com.cis.batch33.library.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name="library_member")
@Entity
@Data
public class LibraryMember {

    @Id
    @Column(name="member_id")
    private Integer memberId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email_address")
    private String emailAddress;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="membership_level")
    private String memberShipLevel;




    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "address_id")
    private Address address;



    @OneToMany(mappedBy = "libraryMember")
    private List<Checkout> checkouts;
    // lombok
}


// xml for mapping , connection details
// url , username, password and driver name

// mapping, dependencies, connection properties
// JPA REPOSITORIES
// autowire and use it in service layer




// query, execute
// EntityManager, EntityManagerFactory -
// execute, executeUpdate , list,
