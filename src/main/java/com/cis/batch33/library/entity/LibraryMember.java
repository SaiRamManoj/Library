package com.cis.batch33.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

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
    private Long phoneNumber;

    @Column(name="membership_level")
    private String memberShipLevel;

    @Column(name="address_id")
    private Long addressId;

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
