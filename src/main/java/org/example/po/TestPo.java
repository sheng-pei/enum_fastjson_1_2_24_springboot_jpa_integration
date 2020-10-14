package org.example.po;

import org.example.enums.TestEnum;

import javax.persistence.*;

@Entity
@Table(name = "enum_fastjson_1_2_24_springboot_jpa_integration")
public class TestPo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
