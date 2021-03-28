package com.product.testScripts;

import com.product.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@SpringBootTest(classes = Application.class)
@SpringBootApplication
public class CaseBase extends AbstractTestNGSpringContextTests {

    public CaseBase(){

    }
}
