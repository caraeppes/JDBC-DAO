package daos;

import models.Latte;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class AppRunner {

    static final String databaseURL = "jdbc:mysql://localhost:3306/starbucks";
    static final String user = "root";
    static final String password = "root";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(databaseURL, user, password);) {
            LatteDAO latteDAO = new LatteDAO(conn);

            // CREATE
            Latte createLatte = new Latte();
            createLatte.setSize("tall");
            createLatte.setShots(3);
            createLatte.setFlavor("vanilla");
            createLatte.setMilk("whole");
            createLatte.setTemperature("hot");

            System.out.println(latteDAO.create(createLatte));

            // READ ONE
            Latte readLatte = latteDAO.findById(1);
            System.out.println(readLatte);

            // READ ALL
            List<Latte> lattes = latteDAO.findAll();
            for(Latte latte : lattes){
                System.out.println(latte);
            }

            // UDPATE
            Latte updateLatte = latteDAO.findById(2);
            updateLatte.setSize("tall");
            latteDAO.update(updateLatte);

            // DELETE
            latteDAO.delete(10);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


