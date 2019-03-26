package daos;

import models.Latte;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LatteDAO extends DAO<Latte>{

    private static final String INSERT = "INSERT INTO latte" +
            "(size, shots, flavor, milk, temperature)" +
            "values(?,?,?,?,?)";
    private static final String FIND_ONE = "SELECT * FROM latte WHERE id = ?";
    private static final String FIND_ALL = "SELECT * FROM latte";
    private static final String UPDATE = "UPDATE latte SET id = ?, size = ?, shots = ?, flavor = ?, milk = ?, temperature = ?  WHERE id = ?";
    private static final String DELETE = "DELETE FROM latte WHERE id = ?";

    public LatteDAO(Connection connection) {
        super(connection);
    }


    public Latte findById(int id) {
        Latte latte = null;
        try(PreparedStatement pstmt = connection.prepareStatement(FIND_ONE)){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                latte = getLatteFromResultSet(rs);

            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return latte;
    }

    public List<Latte> findAll() {
        List<Latte> lattes = new ArrayList<>();
        Latte latte = null;

        try (PreparedStatement pstmt = connection.prepareStatement(FIND_ALL);) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                latte = getLatteFromResultSet(rs);
                lattes.add(latte);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lattes;
    }

    public Latte update(Latte dto) {
        Latte latte = null;
        try(PreparedStatement pstmt = this.connection.prepareStatement(UPDATE)){
            pstmt.setInt(1, dto.getId());
            pstmt.setString(2, dto.getSize());
            pstmt.setInt(3, dto.getShots());
            pstmt.setString(4, dto.getFlavor());
            pstmt.setString(5, dto.getMilk());
            pstmt.setString(6, dto.getTemperature());
            pstmt.setInt(7, dto.getId());
            pstmt.executeUpdate();
            latte = this.findById(dto.getId());
        }catch (SQLException e){
            e.printStackTrace();
        }
        return latte;
    }

    public Latte create(Latte dto) {
        int key = -1;
        try(PreparedStatement pstmt = this.connection.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)){
            pstmt.setString(1, dto.getSize());
            pstmt.setInt(2, dto.getShots());
            pstmt.setString(3, dto.getFlavor());
            pstmt.setString(4, dto.getMilk());
            pstmt.setString(5, dto.getTemperature());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            if (rs != null && rs.next()) {
                key = rs.getInt(1);
            }
        }catch(SQLException e){
           e.printStackTrace();
        }
        return this.findById(key);
    }

    public void delete(int id) {
        try(PreparedStatement pstmt = this.connection.prepareStatement(DELETE)){
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }catch(SQLException e){
           e.printStackTrace();
        }
    }

    public Latte getLatteFromResultSet(ResultSet rs) throws SQLException {
        Latte latte = new Latte();
        latte.setId(rs.getInt("id"));
        latte.setSize(rs.getString("size"));
        latte.setShots(rs.getInt("shots"));
        latte.setFlavor(rs.getString("flavor"));
        latte.setMilk(rs.getString("milk"));
        latte.setTemperature(rs.getString("temperature"));
        return latte;
    }


}
