package db.dao.mysql;

import db.ConnectionPool.ConnectionPool;
import db.dao.ICountriesDao;
import db.models.Countries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CountriesDao implements ICountriesDao {
    private static final Logger LOGGER = LogManager.getLogger(CountriesDao.class);

    public List<Countries> getAllEntities(){

        List<Countries> countryList = new ArrayList<>();
        String sql = "SELECT * FROM countries";
        Connection con = ConnectionPool.getInstance().getConnection();

        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Countries country = new Countries();
                country.setCountryId(rs.getInt(1));
                country.setCountryName(rs.getString(2));

                countryList.add(country);
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

        return countryList;
    }

    public Countries getEntityById(int id) {

        Countries country = null;
        Connection con = ConnectionPool.getInstance().getConnection();
        String sql = "SELECT * FROM countries WHERE country_id = (?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int countryId = rs.getInt("country_id");
                String countryName = rs.getString("country_name");

                country = new Countries(countryId, countryName);
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

        return country;
    }

    public Countries createEntity(Countries country) {
        Connection con = ConnectionPool.getInstance().getConnection();
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
                    ConnectionPool.getInstance().releaseConnection(con);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
        }

        return null;
    }

    public void updateEntity(Countries country) {
        String sql = "UPDATE countries SET country_name = (?) WHERE country_id = (?)";
        Connection con = ConnectionPool.getInstance().getConnection();

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
                    ConnectionPool.getInstance().releaseConnection(con);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
        }
    }

    public void removeEntity(int id) {
        String sql = "Delete FROM countries WHERE country_id = (?)";
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
