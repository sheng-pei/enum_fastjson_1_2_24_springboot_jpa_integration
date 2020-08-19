package org.example.po;

import org.example.enums.TestEnum;

import javax.persistence.*;

@Entity
@Table(name = "test")
public class TestPo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "test")
    private TestEnum test;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TestEnum getTest() {
        return test;
    }

    public void setTest(TestEnum test) {
        this.test = test;
    }

}
