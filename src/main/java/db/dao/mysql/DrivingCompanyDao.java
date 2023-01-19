package db.dao.mysql;

import db.ConnectionPool.ConnectionPool;
import db.dao.IDrivingCompanyDAO;
import db.models.DrivingCompany;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DrivingCompanyDao implements IDrivingCompanyDAO {
    private static final Logger LOGGER = LogManager.getLogger(DrivingCompanyDao.class);

    public List<DrivingCompany> getAllEntities(){

        List<DrivingCompany>  dcList = new ArrayList<>();
        String sql = "SELECT * FROM driving_companies";
        Connection con = ConnectionPool.getInstance().getConnection();

        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DrivingCompany dc = new DrivingCompany();
                dc.setDrivingId(rs.getInt(1));
                dc.setPrice(rs.getDouble(2));
                dc.setCityId(rs.getInt(3));

                dcList.add(dc);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            if (con != null) {
                try {
                    ConnectionPool.getInstance().releaseConnection(con);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
        }

        return dcList;
    }

    public DrivingCompany getEntityById(int id) {

        DrivingCompany dc = null;
        Connection con = ConnectionPool.getInstance().getConnection();
        String sql = "SELECT * FROM driving_companies WHERE driving_id = (?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int drivingId = rs.getInt("driving_id");
                double price = rs.getDouble("price");
                int cityId = rs.getInt("city_id");

                dc = new DrivingCompany(drivingId, price, cityId);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            if (con != null) {
                try {
                    ConnectionPool.getInstance().releaseConnection(con);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
        }

        return dc;
    }

    public DrivingCompany createEntity(DrivingCompany dc) {
        Connection con = ConnectionPool.getInstance().getConnection();
        String sql = "INSERT INTO driving_companies (driving_id, price, city_id) VALUES (?,?,?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, dc.getDrivingId());
            ps.setDouble(2, dc.getPrice());
            ps.setInt(3, dc.getCityId());
            ps.executeUpdate();

            LOGGER.info("Insertion was successful");
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            if (con != null) {
                try {
                    ConnectionPool.getInstance().releaseConnection(con);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
        }

        return null;
    }

    public void updateEntity(DrivingCompany dc) {
        String sql = "UPDATE driving_companies SET price = (?), city_id = (?) WHERE driving_id = (?)";
        Connection con = ConnectionPool.getInstance().getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            LOGGER.info("in update customer: " + dc);

            ps.setDouble(1, dc.getPrice());
            ps.setInt(2, dc.getCityId());
            ps.setInt(3, dc.getDrivingId());
            ps.execute();
        } catch(Exception e) {
            LOGGER.error(e);
        } finally {
            if (con != null) {
                try {
                    ConnectionPool.getInstance().releaseConnection(con);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
        }
    }

    public void removeEntity(int id) {
        String sql = "Delete FROM driving_companies WHERE driving_id = (?)";
        Connection con = ConnectionPool.getInstance().getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, id);
            ps.executeUpdate();

            LOGGER.info("Removal was successful");
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            if (con != null) {
                try {
                    ConnectionPool.getInstance().releaseConnection(con);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
        }
    }
}
