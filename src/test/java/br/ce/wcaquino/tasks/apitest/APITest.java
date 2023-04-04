package br.ce.wcaquino.tasks.apitest;

import static org.hamcrest.CoreMatchers.is;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;

public class APITest {	
	
	@BeforeClass
	public static void setup() {
		RestAssured.baseURI="http://localhost:8001/tasks-backend";
	}

	@Test
	public void deveRetornarStatus200() {
		RestAssured
		.given()
		.when()
			.get("/todo")
		.then()
			.statusCode(200);
	}
	
	@Test
	public void deveCriarUmaTask() {
		RestAssured
		.given()
			.body("{ \"task\": \"Testar a inserção\", \"dueDate\": \"2023-10-10\"}")
			.contentType("application/json")
		.when()
			.post("/todo")
		.then()
			.statusCode(201);
	}
	
	@Test
	public void naoDeveCriarUmaTask() {
		RestAssured
		.given()
			.body("{ \"task\": \"Testar a inserção\", \"dueDate\": \"2023-01-01\"}")
			.contentType("application/json")
		.when()
			.post("/todo")
		.then()
			.statusCode(400)
			.body("message", is("Due date must not be in past"));
	}
}
