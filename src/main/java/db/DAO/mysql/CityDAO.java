package db.dao.mysql;

import db.connectionpoolm.ConnectionPool;
import db.dao.ICityDAO;
import db.models.City;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CityDAO implements ICityDAO {
    private static final Logger LOGGER = LogManager.getLogger(CityDAO.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public List<City> getAllEntities(){
        List<City> cityList = new ArrayList<>();
        String sql = "SELECT * FROM cities";
        Connection con = connectionPool.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                City city = new City();
                city.setCityId(rs.getInt(1));
                city.setCityName(rs.getString(2));
                city.setCountryId(rs.getInt(3));
                cityList.add(city);
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
        return cityList;
    }

    public City getEntityById(int id) {
        City city = null;
        Connection con = connectionPool.getConnection();
        String sql = "SELECT * FROM cities WHERE company_id = (?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int cityId = rs.getInt("city_id");
                String cityName = rs.getString("city_name");
                int countryId = rs.getInt("country_id");
                city = new City(cityId, cityName, countryId);
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
        return city;
    }

    public City getCityByName(String name) {
        City city = null;
        Connection con = connectionPool.getConnection();
        String sql = "SELECT * FROM cities WHERE city_name = (?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int cityId = rs.getInt("city_id");
                String cityName = rs.getString("city_name");
                int countryId = rs.getInt("country_id");
                city = new City(cityId, cityName, countryId);
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
        return city;
    }

    public City createEntity(City city) {
        Connection con = connectionPool.getConnection();;
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
                    connectionPool.releaseConnection(con);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
        }
        return null;
    }

    public void updateEntity(City city) {
        String sql = "UPDATE cities SET city_name = (?), country_id = (?) WHERE city_id = (?)";
        Connection con = connectionPool.getConnection();
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
                    connectionPool.releaseConnection(con);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
        }
    }

    public void removeEntity(int id) {
        String sql = "Delete FROM cities WHERE city_id = (?)";
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
