package com.cherkasov;

import javax.persistence.*;

@Entity
@Table(name = "son")
public class Son {
    @SequenceGenerator(name = "mainSequence", sequenceName = "GENERAL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mainSequence")
    @Id
    private Integer id;
    @Column
    private String name;
    @Column
    private int age;
}
