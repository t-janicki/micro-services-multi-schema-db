package com.credit.domain;

import javax.persistence.*;

@Entity
@Table(name = "credits")
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String creditName;

    @Column(unique = true, nullable = false)
    private Integer creditId;

    public Credit(String creditName, Integer creditId) {
        this.creditName = creditName;
        this.creditId = creditId;
    }

    public Credit() {

    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getCreditName() {
        return creditName;
    }

    public void setCreditName(String creditName) {
        this.creditName = creditName;
    }

    public Integer getCreditId() {
        return creditId;
    }

    public void setCreditId(Integer creditId) {
        this.creditId = creditId;
    }
}
