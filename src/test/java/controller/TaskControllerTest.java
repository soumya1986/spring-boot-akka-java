package controller;

import com.example.Application;
import com.example.repository.TaskRepository;
import com.jayway.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;

/**
 * Created by soumya on 5/18/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TaskControllerTest {

    @Autowired
    TaskRepository taskRepository;

    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp(){
        taskRepository.deleteAll();
        RestAssured.port = port;
    }

    @Test
    public void test01_shouldDistributeTask(){
        try {
                    when()
                    .get("/task/distribute")
                    .then()
                    .statusCode(HttpStatus.SC_OK);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
