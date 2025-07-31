package dubinin.sergey.testContainers;

import dubinin.sergey.pg.Migration;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PostgreSQLSelectMigratedDataTest {

    @Test
    void canSelectMigratedDataFromPostgreSQLTestContainer() {

        var container = CommonPostgreSQLContainer.getInstance();

        Migration.addMigration(container, "classpath:db/migration");

        try (Connection connection = DriverManager.getConnection(
                container.getJdbcUrl(),
                container.getUsername(),
                container.getPassword()
        );
             Statement statement = connection.createStatement();
        ) {
            ResultSet rs = statement.executeQuery("SELECT name FROM users ORDER BY name");

            assertAll(
                    () -> {
                        rs.next();
                        assertThat(rs.getString("name")).isEqualTo("Alice");
                    },
                    () -> {
                        rs.next();
                        assertThat(rs.getString("name")).isEqualTo("Bob");
                    },
                    () -> {
                        rs.next();
                        assertThat(rs.getString("name")).isEqualTo("Charlie");
                    }
            );

        } catch (SQLException e){
            e.printStackTrace();
        }

    }
}
