package org.example;

import com.alibaba.fastjson.JSON;
import org.example.controller.TestParam;
import org.example.enums.TestEnum;
import org.example.po.TestPo;
import org.example.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    @Autowired
    private TestRepository testRepository;

    private void save() {
        TestPo testPo = new TestPo();
        testPo.setTest(TestEnum.A);
        testRepository.save(testPo);
    }

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        Application app = context.getBean(Application.class);
//        app.save();
    }

}
