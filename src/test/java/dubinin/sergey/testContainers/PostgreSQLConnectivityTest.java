package dubinin.sergey.testContainers;

import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.assertj.core.api.Assertions.assertThat;

public class PostgreSQLConnectivityTest {

    @Test
    void canConnectToPostgresContainer() {

        var container = CommonPostgreSQLContainer.getInstance();

        try (Connection connection = DriverManager.getConnection(
                container.getJdbcUrl(),
                container.getUsername(),
                container.getPassword()
        );
             Statement statement = connection.createStatement();
        ) {
            ResultSet rs = statement.executeQuery("SELECT * FROM test_db");

            assertThat(rs.next()).isTrue();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
