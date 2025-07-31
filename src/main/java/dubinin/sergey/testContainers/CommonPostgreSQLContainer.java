package dubinin.sergey.testContainers;

import org.testcontainers.containers.PostgreSQLContainer;

public class CommonPostgreSQLContainer {

    private static final PostgreSQLContainer<?> postgresContainer;

    static {
        postgresContainer = new PostgreSQLContainer<>("postgres:15")
                .withDatabaseName("test_db")
                .withUsername("test_user")
                .withPassword("test_pass");
        postgresContainer.start();
    }

    public static PostgreSQLContainer<?> getInstance(){
        return postgresContainer;
    }

}
