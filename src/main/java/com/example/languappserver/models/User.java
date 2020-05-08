package com.example.languappserver.models;

import lombok.*;

import javax.persistence.*;
import javax.swing.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = {"id"})
@Table(name = "user", schema = "public")
public class User {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "pass")
    private String password;
    @Column(name = "mail")
    private String mail;
    @Column(name = "phone")
    private String phone;
}
