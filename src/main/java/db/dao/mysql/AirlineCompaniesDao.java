package db.dao.mysql;

import db.ConnectionPool.ConnectionPool;
import db.dao.IBaseAirlineCompanies;
import db.models.AirlineCompanies;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AirlineCompaniesDao implements IBaseAirlineCompanies {
    private static final Logger LOGGER = LogManager.getLogger(AirlineCompaniesDao.class);

    public List<AirlineCompanies> getAllEntities(){

        List<AirlineCompanies> acList = new ArrayList<>();
        String sql = "SELECT company_id, company_name FROM airline_companies";
        Connection con = ConnectionPool.getInstance().getConnection();

        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                AirlineCompanies ac = new AirlineCompanies();
                ac.setCompanyId(rs.getInt(1));
                ac.setCompanyName(rs.getString(2));

                acList.add(ac);
            }

            // release connection in finally block and check if null
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

        return acList;
    }

    public AirlineCompanies getEntityById(int id) {

        AirlineCompanies ac = null;
        Connection con = ConnectionPool.getInstance().getConnection();
        String sql = "SELECT * FROM airline_companies WHERE company_id = (?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int acId = rs.getInt("company_id");
                String companyName = rs.getString("company_name");

                ac = new AirlineCompanies(acId, companyName);
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

        return ac;
    }

    public AirlineCompanies getEntityByName(String name) {
        AirlineCompanies ac = null;
        Connection con = ConnectionPool.getInstance().getConnection();
        String sql = "SELECT * FROM airline_companies WHERE company_name = (?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(2, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int acId = rs.getInt("company_id");
                String companyName = rs.getString("company_name");

                ac = new AirlineCompanies(acId, companyName);
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

        return ac;
    }

    public AirlineCompanies createEntity(AirlineCompanies ac) {
        Connection con = ConnectionPool.getInstance().getConnection();
        String sql = "INSERT INTO airline_companies (company_id, company_name) VALUES (?,?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, ac.getCompanyId());
            ps.setString(2, ac.getCompanyName());

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

    public void updateEntity(AirlineCompanies ac) {
        String sql = "UPDATE airline_companies SET company_name = (?) WHERE company_id = (?)";
        Connection con = ConnectionPool.getInstance().getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            LOGGER.info("in update customer: " + ac);

            ps.setString(1, ac.getCompanyName());
            ps.setInt(2, ac.getCompanyId());
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
        String sql = "Delete FROM airline_companies WHERE company_id = (?)";
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
