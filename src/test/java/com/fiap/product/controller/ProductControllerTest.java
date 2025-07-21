package com.fiap.product.controller;

import com.fiap.product.controller.json.ProductRequestDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductControllerTest {

    static UUID createdId;

    @Test
    @Order(1)
    void testCreateProduct() {
        ProductRequestDTO dto = new ProductRequestDTO("Produto 1", "SKU001", new BigDecimal("99.99"));

        createdId =
                UUID.fromString(given()
                        .contentType(ContentType.JSON)
                        .body(dto)
                        .when()
                        .post("/products")
                        .then()
                        .statusCode(201)
                        .body("name", Matchers.equalTo("Produto 1"))
                        .body("sku", Matchers.equalTo("SKU001"))
                        .body("price", Matchers.equalTo(99.99f))
                        .extract().path("id"));
    }

    @Test
    @Order(2)
    void testCreateDuplicatedProductSku() {
        ProductRequestDTO dto = new ProductRequestDTO("Produto 2", "SKU001", new BigDecimal("78.90"));

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/products")
                .then()
                .statusCode(409)
                .body("errorCode", Matchers.equalTo("SKU_ALREADY_IN_USE"))
                .body("message", Matchers.equalTo("SKU already exists for another product: SKU001"))
                .body("status", Matchers.equalTo(409));
    }

    @Test
    @Order(3)
    void testGetAllProducts() {
        when()
                .get("/products")
                .then()
                .statusCode(200)
                .body("size()", Matchers.greaterThan(0));
    }

    @Test
    @Order(4)
    void testGetProductById() {
        given()
                .pathParam("id", createdId)
                .when()
                .get("/products/{id}")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(createdId.toString()));
    }

    @Test
    @Order(5)
    void testUpdateProduct() {
        ProductRequestDTO update = new ProductRequestDTO("Produto Atualizado", "SKU999", new BigDecimal("150.0"));

        given()
                .contentType(ContentType.JSON)
                .body(update)
                .pathParam("id", createdId)
                .when()
                .put("/products/{id}")
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo("Produto Atualizado"))
                .body("sku", Matchers.equalTo("SKU999"))
                .body("price", Matchers.equalTo(150.0f));
    }

    @Test
    @Order(6)
    void testDeleteProduct() {
        given()
                .pathParam("id", createdId)
                .when()
                .delete("/products/{id}")
                .then()
                .statusCode(204);
    }

    @Test
    @Order(7)
    void testDeleteProductInexistent() {
        given()
                .pathParam("id", createdId)
                .when()
                .delete("/products/{id}")
                .then()
                .statusCode(404);
    }

    @Test
    @Order(8)
    void testUpdateProductInvalidId() {
        ProductRequestDTO update = new ProductRequestDTO("Produto Atualizado", "SKU999", new BigDecimal("150.0"));

        given()
                .contentType(ContentType.JSON)
                .body(update)
                .pathParam("id", createdId)
                .when()
                .put("/products/{id}")
                .then()
                .statusCode(404)
                .body("errorCode", Matchers.equalTo("PRODUCT_NOT_FOUND"))
                .body("message", Matchers.equalTo("Product not found: " + createdId))
                .body("status", Matchers.equalTo(404));
    }

    @Test
    @Order(9)
    void testGetNotFound() {
        given()
                .pathParam("id", UUID.randomUUID())
                .when()
                .get("/products/{id}")
                .then()
                .statusCode(404);
    }

    @Test
    @Order(10)
    void testCreateInvalidProductName() {
        ProductRequestDTO dto = new ProductRequestDTO("", "", new BigDecimal("-10.0"));

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/products")
                .then()
                .statusCode(400)
                .body("errorCode", Matchers.equalTo("INVALID_PRODUCT_NAME"))
                .body("message", Matchers.equalTo("Invalid product name"))
                .body("status", Matchers.equalTo(400));
    }

    @Test
    @Order(11)
    void testCreateInvalidProductSku() {
        ProductRequestDTO dto = new ProductRequestDTO("Produto", "", new BigDecimal("-10.0"));

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/products")
                .then()
                .statusCode(400)
                .body("errorCode", Matchers.equalTo("INVALID_PRODUCT_SKU"))
                .body("message", Matchers.equalTo("Invalid SKU code"))
                .body("status", Matchers.equalTo(400));
    }

    @Test
    @Order(12)
    void testCreateInvalidProductPrice() {
        ProductRequestDTO dto = new ProductRequestDTO("Produto", "SKU001", new BigDecimal("-10.0"));

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/products")
                .then()
                .statusCode(400)
                .body("errorCode", Matchers.equalTo("INVALID_PRODUCT_PRICE"))
                .body("message", Matchers.equalTo("Invalid product price: -10.0"))
                .body("status", Matchers.equalTo(400));
    }

}