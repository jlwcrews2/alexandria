package no.jlwcrews.alexandria.location;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

@SpringBootTest
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LocationServiceTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:alpine");

    @Autowired
    LocationService locationService;

    @Test
    @Order(1)
    void setup(){
        for (int i = 0; i < 10; i++) {
            locationService.saveLocation(new Location("Location " + i));
        }
    }

    @Test
    @Order(2)
    void getLocations() {
        List<Location> locations = locationService.getLocations();
        assert locations.size() == 10;
    }

    @Test
    @Order(3)
    void getLocationById() {
        var location = locationService.getLocationById(1);
        assert location != null;
        assert location.getLocation().equals("Location 0");
    }

    @Test
    @Order(4)
    void saveLocation() {
        assert locationService.saveLocation(new Location("Location 110")).getLocation().equals("Location 110");
    }

    @Test
    @Order(5)
    void deleteLocationById() {
        locationService.deleteLocationById(1);
        assert locationService.getLocations().size() == 10;
    }
}