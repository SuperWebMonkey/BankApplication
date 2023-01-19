package db.DAO.mysql;

import db.ConnectionPool.ConnectionPool;
import db.DAO.IBaseAirlineCompanyDAO;
import db.models.AirlineCompany;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AirlineCompanyDAO implements IBaseAirlineCompanyDAO {
    private static final Logger LOGGER = LogManager.getLogger(AirlineCompanyDAO.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public List<AirlineCompany> getAllEntities(){
        List<AirlineCompany> acList = new ArrayList<>();
        String sql = "SELECT company_id, company_name FROM airline_companies";
        Connection con = connectionPool.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AirlineCompany ac = new AirlineCompany();
                ac.setCompanyId(rs.getInt(1));
                ac.setCompanyName(rs.getString(2));
                acList.add(ac);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            if (con != null) {
                try {
                    connectionPool.releaseConnection(con);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
        }
        return acList;
    }

    public AirlineCompany getEntityById(int id) {
        AirlineCompany ac = null;
        Connection con = connectionPool.getConnection();
        String sql = "SELECT * FROM airline_companies WHERE company_id = (?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int acId = rs.getInt("company_id");
                String companyName = rs.getString("company_name");
                ac = new AirlineCompany(acId, companyName);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            if (con != null) {
                try {
                    connectionPool.releaseConnection(con);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
        }
        return ac;
    }

    public AirlineCompany getEntityByName(String db_name) {
        AirlineCompany ac = null;
        Connection con = connectionPool.getConnection();
        String sql = "SELECT * FROM airline_companies WHERE company_name = (?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, db_name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int acId = rs.getInt("company_id");
                String companyName = rs.getString("company_name");
                ac = new AirlineCompany(acId, companyName);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            if (con != null) {
                try {
                    connectionPool.releaseConnection(con);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
        }
        return ac;
    }

    public AirlineCompany createEntity(AirlineCompany ac) {
        Connection con = connectionPool.getConnection();
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
                    connectionPool.releaseConnection(con);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
        }
        return null;
    }

    public void updateEntity(AirlineCompany ac) {
        String sql = "UPDATE airline_companies SET company_name = (?) WHERE company_id = (?)";
        Connection con = connectionPool.getConnection();
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
                    connectionPool.releaseConnection(con);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
        }
    }

    public void removeEntity(int id) {
        String sql = "Delete FROM airline_companies WHERE company_id = (?)";
        Connection con = connectionPool.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
            LOGGER.info("Removal was successful");
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            if (con != null) {
                try {
                    connectionPool.releaseConnection(con);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
        }
    }
}
