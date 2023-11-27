package com.example.collaboration;

import com.example.collaboration.controller.ResearchController;
import com.example.collaboration.entity.Manager;
import com.example.collaboration.entity.Research;
import com.example.collaboration.service.ResearchService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import org.junit.Before;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class ResearchControllerTest {

    @InjectMocks
    private ResearchController researchController;
    @Mock
    private ResearchService researchService;

    private static long researchId;

    @Test
    @DisplayName("Create Research Controller")
    @Order(1)
    public void testCreateResearch() {

        Research inputResearch = new Research();
        Research outputResearch = new Research();

        when(researchService.createResearch(inputResearch)).thenReturn(outputResearch);

        ResponseEntity<Research> response = researchController.createResearch(inputResearch);

        assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Status Code Attribute");
        assertEquals(outputResearch, response.getBody(), "Response Attribute");
    }

    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Test
    @DisplayName("Create Research API")
    @Order(2)
    public void testCreateResearchAPI() {

        Research testResearch = new Research();

        testResearch.setDescription("test");
        testResearch.setSubject("test");
        testResearch.setSalary(0.0);
        testResearch.setStartDate(null);
        Manager manager = new Manager();
        manager.setEmail("managertest@test.test");
        testResearch.setManager(manager);

        ValidatableResponse response = given()
                .contentType(ContentType.JSON)
                .body(testResearch)
                .when()
                .post("/research/create")
                .then()
                .statusCode(201)
                .body("subject", equalTo(testResearch.getSubject()))
                .contentType(ContentType.JSON);

        Research res = response.extract().as(Research.class);

        researchId = res.getResearchId();
    }

    @Test
    @DisplayName("Get Research API")
    @Order(3)
    public void testGetResearchAPI(){
        given()
                .get("/research/getById?researchId="+researchId)
                .then()
                .statusCode(302)
                .body("subject", equalTo("test"))
                .body("description", equalTo("test"))
                .body("manager.email", equalTo("managertest@test.test"));
    }

    @Test
    @DisplayName("Update Research API")
    @Order(4)
    public void testUpdateResearchAPI(){
        Research testUpdateResearch = new Research();
        testUpdateResearch.setResearchId(researchId);
        testUpdateResearch.setSubject("UPDATED");
        testUpdateResearch.setDescription("UPDATED");

        given()
                .contentType(ContentType.JSON)
                .body(testUpdateResearch)
                .when()
                .put("/research/update?researchId="+researchId)
                .then()
                .statusCode(200)
                .body("subject", equalTo(testUpdateResearch.getSubject()))
                .body("description", equalTo(testUpdateResearch.getDescription()))
                .contentType(ContentType.JSON);
    }

    @Test
    @DisplayName("Delete Research API")
    @Order(5)
    public void testDeleteResearchAPI(){
        given()
                .delete("/research/delete?researchId="+researchId)
                .then()
                .statusCode(200)
                .body(equalTo("Deleted Successfully"))
                .contentType(ContentType.TEXT);
    }

}
