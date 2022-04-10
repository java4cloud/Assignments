package com.jp.sp.controller;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.File;

import static io.restassured.RestAssured.given;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FileValidationControllerTest {

    @LocalServerPort
    int springBootPort = 0;

    @Test
    @DisplayName("CSV file upload : success with status code 200")
    void csv_File_Upload_With_SuccessResponse() {
        given()
                .multiPart("file", new File("src/test/resources/testfile/records.csv"))
                .accept(ContentType.JSON)
                .when()
                .port(springBootPort)
                .post("/upload")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("JSON file upload : success with status code 200")
    void json_File_Upload_With_SuccessResponse() {
        given()
                .multiPart("file", new File("src/test/resources/testfile/records.json"))
                .accept(ContentType.JSON)
                .when()
                .port(springBootPort)
                .post("/upload")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Html file upload : failure with status code 400")
    void html_File_Upload_With_SuccessResponse() {
        given()
                .multiPart("file", new File("src/test/resources/testfile/instructions.html"))
                .accept(ContentType.JSON)
                .when()
                .port(springBootPort)
                .post("/upload")
                .then()
                .statusCode(400);
    }
}