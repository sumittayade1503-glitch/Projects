package hospitalManagmentSystem;

import java.sql.Connection;

public class DatabaseEntity {
    protected Connection connection; 

    public DatabaseEntity(Connection connection) {
        this.connection = connection;
    }
}