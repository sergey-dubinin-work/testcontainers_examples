package dubinin.sergey.pg;

import org.flywaydb.core.Flyway;
import org.testcontainers.containers.PostgreSQLContainer;

public class Migration {

    public static void addMigration(PostgreSQLContainer<?> postgreSQLContainer, String path) {
        Flyway flyway = Flyway.configure()
                .dataSource(
                        postgreSQLContainer.getJdbcUrl(),
                        postgreSQLContainer.getUsername(),
                        postgreSQLContainer.getPassword()
                )
                .locations(path)
                .load();

        flyway.migrate();
    }

}
