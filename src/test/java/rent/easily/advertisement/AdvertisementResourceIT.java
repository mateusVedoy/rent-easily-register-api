package rent.easily.advertisement.application.controller;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import rent.easily.advertisement.application.dto.AdvertisementDTO;

import rent.easily.advertisement.infrastructure.database.AdvertisementRepository;
import rent.easily.property.infrastructure.database.PropertyRepository;
import rent.easily.user.infrastructure.database.UserRepository;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@Transactional
class AdvertisementResourceIT {

    private Integer userId;
    private Integer propertyId;



    void setupEnvironment(String cpf, String zipCode) {
        // --- ETAPA 1: Criar Usuário ---
        Map<String, Object> credentials = Map.of(
            "mail", "mateus@mail.com",
            "password", "123p2ss"
        );
        Map<String, Object> userDto = Map.of(
            "fullName", "mateus", "cpf", cpf,
            "income", 150.99, "registerType", "lessor",
            "credentials", credentials
        );

        this.userId = given()
            .contentType(ContentType.JSON)
            .body(userDto)
        .when()
            .post("/user/create")
        .then()
            .statusCode(201)
            .extract().path("data[0].id");

        // --- ETAPA 2: Criar Propriedade ---
        Map<String, Object> addressDto = Map.of(
            "country", "Brasil", "state", "RS", "city", "Cidade do imóvel",
            "neighborhood", "Bairro do imóvel", "street", "Rua do imóvel",
            "streetNumber", 123, "zipCode", zipCode
        );
        Map<String, Object> propertyDto = Map.of(
            "description", "descrição do imóvel 2",
            "userId", this.userId,
            "registryId", "3573457347",
            "address", addressDto
        );

        this.propertyId = given()
            .contentType(ContentType.JSON)
            .body(propertyDto)
        .when()
            .post("/property/create")
        .then()
            .statusCode(201)
            .extract().path("data[0].id");
    }


    @Test
    @DisplayName("Deve criar um anúncio com sucesso (POST /advertisement/create)")
    void shouldCreateAdvertisementSuccessfully() {

        setupEnvironment("123.123.123-12", "3463636");

        AdvertisementDTO dto = new AdvertisementDTO(2000.0, "Casa ampla", (long) this.propertyId);

        given()
            .contentType(ContentType.JSON)
            .body(dto)
        .when()
            .post("/advertisement/create")
        .then()
            .statusCode(201)
            .body("status", equalTo(201))
            .body("data[0].rentAmount", equalTo(2000.0F))
            .body("data[0].propertyId", equalTo(this.propertyId));
    }

    @Test
    @DisplayName("Deve retornar erro ao criar anúncio inválido (POST /advertisement/create)")
    void shouldReturnErrorWhenCreatingInvalidAdvertisement() {

        setupEnvironment("123.123.123-14", "3463656");
        
        AdvertisementDTO invalidDto = new AdvertisementDTO(0.0, "", null);

        given()
            .contentType(ContentType.JSON)
            .body(invalidDto)
        .when()
            .post("/advertisement/create")
        .then()
            .statusCode(400)
            .body("status", equalTo(400));
    }

    @Test
    @DisplayName("Não deve retornar lista vazia quando há anúncios (GET /advertisement/find/all)")
    void shouldNotReturnEmptyListWhenHasAdvertisements() {

        setupEnvironment("123.123.123-13", "3463644");

        given()
            .accept(ContentType.JSON)
        .when()
            .get("/advertisement/find/all")
        .then()
            .statusCode(200)
            .body("status", equalTo(200))
            .body("data", not(empty()));
    }
}