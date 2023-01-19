package db.DAO.mysql;

import db.ConnectionPool.ConnectionPool;
import db.DAO.ICountryDAO;
import db.models.Country;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO implements ICountryDAO {
    private static final Logger LOGGER = LogManager.getLogger(CountryDAO.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public List<Country> getAllEntities(){
        List<Country> countryList = new ArrayList<>();
        String sql = "SELECT * FROM countries";
        Connection con = connectionPool.getConnection();;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Country country = new Country();
                country.setCountryId(rs.getInt(1));
                country.setCountryName(rs.getString(2));
                countryList.add(country);
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
        return countryList;
    }

    public Country getEntityById(int id) {
        Country country = null;
        Connection con = connectionPool.getConnection();
        String sql = "SELECT * FROM countries WHERE country_id = (?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int countryId = rs.getInt("country_id");
                String countryName = rs.getString("country_name");
                country = new Country(countryId, countryName);
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
        return country;
    }

    public Country getEntityByName(String db_name) {
        Country country = null;
        Connection con = connectionPool.getConnection();
        String sql = "SELECT * FROM countries WHERE country_name = (?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, db_name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int countryId = rs.getInt("country_id");
                String countryName = rs.getString("country_name");
                country = new Country(countryId, countryName);
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
        return country;
    }

    public Country createEntity(Country country) {
        Connection con = connectionPool.getConnection();
        String sql = "INSERT INTO countries (country_id, country_name) VALUES (?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, country.getCountryId());
            ps.setString(2, country.getCountryName());
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

    public void updateEntity(Country country) {
        String sql = "UPDATE countries SET country_name = (?) WHERE country_id = (?)";
        Connection con = connectionPool.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            LOGGER.info("in update customer: " + country);
            ps.setString(1, country.getCountryName());
            ps.setInt(2, country.getCountryId());
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
        String sql = "Delete FROM countries WHERE country_id = (?)";
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
