package db.dao.mysql;

import db.ConnectionPool.ConnectionPool;
import db.dao.ICitiesDao;
import db.models.Cities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CitiesDao implements ICitiesDao {
    private static final Logger LOGGER = LogManager.getLogger(CitiesDao.class);

    public List<Cities> getAllEntities(){

        List<Cities> cityList = new ArrayList<>();
        String sql = "SELECT * FROM cities";
        Connection con = ConnectionPool.getInstance().getConnection();

        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cities city = new Cities();
                city.setCityId(rs.getInt(1));
                city.setCityName(rs.getString(2));
                city.setCountryId(rs.getInt(3));

                cityList.add(city);
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

        return cityList;
    }

    public Cities getEntityById(int id) {

        Cities city = null;
        Connection con = ConnectionPool.getInstance().getConnection();
        String sql = "SELECT * FROM cities WHERE company_id = (?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int cityId = rs.getInt("city_id");
                String cityName = rs.getString("city_name");
                int countryId = rs.getInt("country_id");

                city = new Cities(cityId, cityName, countryId);
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

        return city;
    }

    public Cities createEntity(Cities city) {
        Connection con = ConnectionPool.getInstance().getConnection();
        String sql = "INSERT INTO cities (city_id, city_name, country_id) VALUES (?,?,?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, city.getCityId());
            ps.setString(2, city.getCityName());
            ps.setInt(3, city.getCountryId());
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

    public void updateEntity(Cities city) {
        String sql = "UPDATE cities SET city_name = (?), country_id = (?) WHERE city_id = (?)";
        Connection con = ConnectionPool.getInstance().getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            LOGGER.info("in update customer: " + city);

            ps.setString(1, city.getCityName());
            ps.setInt(2, city.getCountryId());
            ps.setInt(3, city.getCityId());
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
        String sql = "Delete FROM cities WHERE city_id = (?)";
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
