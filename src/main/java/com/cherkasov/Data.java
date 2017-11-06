package com.cherkasov;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "data")
public class Data {
    @SequenceGenerator(name = "mainSequence", sequenceName = "GENERAL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mainSequence")
    @Id
    private Integer id;
    @Column
    private String test;
    @Column
    private int counter;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true) //
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private List<Son> sons;

    public String getTest() {

        return test;
    }

    public void setTest(String test) {

        this.test = test;
    }

    public int getCounter() {

        return counter;
    }

    public void setCounter(int counter) {

        this.counter = counter;
    }

    public List<Son> getSons() {

        return sons;
    }

    public void setSons(List<Son> sons) {

        this.sons = sons;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }
}
