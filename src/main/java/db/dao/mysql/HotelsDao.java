package db.dao.mysql;

import db.ConnectionPool.ConnectionPool;
import db.dao.IHotelsDao;
import db.models.Flights;
import db.models.Hotels;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HotelsDao implements IHotelsDao {
    private static final Logger LOGGER = LogManager.getLogger(HotelsDao.class);

    public List<Hotels> getAllEntities(){

        List<Hotels>  hotelList = new ArrayList<>();
        String sql = "SELECT * FROM hotels";
        Connection con = ConnectionPool.getInstance().getConnection();

        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Hotels hotel = new Hotels();
                hotel.setHotelId(rs.getInt(1));
                hotel.setHotelName(rs.getString(2));
                hotel.setPrice(rs.getDouble(3));
                hotel.setCityId(rs.getInt(4));

                hotelList.add(hotel);
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

        return hotelList;
    }

    public Hotels getEntityById(int id) {

        Hotels hotel = null;
        Connection con = ConnectionPool.getInstance().getConnection();
        String sql = "SELECT * FROM hotels WHERE hotel_id = (?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int hotelId = rs.getInt("hotel_id");
                String hotelName = rs.getString("hotel_name");
                double price = rs.getDouble("price");
                int cityId = rs.getInt("city_id");

                hotel = new Hotels(hotelId, hotelName, price, cityId);
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

        return hotel;
    }

    public Hotels createEntity(Hotels hotel) {
        Connection con = ConnectionPool.getInstance().getConnection();
        String sql = "INSERT INTO hotels (hotel_id, hotel_name, price, city_id) VALUES (?,?,?,?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, hotel.getHotelId());
            ps.setString(2, hotel.getHotelName());
            ps.setDouble(3, hotel.getPrice());
            ps.setInt(4, hotel.getCityId());
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

    public void updateEntity(Hotels hotel) {
        String sql = "UPDATE hotels SET hotel_name = (?), price = (?), city_id = (?), " +
                "WHERE hotel_id = (?)";
        Connection con = ConnectionPool.getInstance().getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            LOGGER.info("in update customer: " + hotel);

            ps.setString(1, hotel.getHotelName());
            ps.setDouble(2, hotel.getPrice());
            ps.setInt(3, hotel.getCityId());
            ps.setInt(4, hotel.getCityId());
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
        String sql = "Delete FROM hotels WHERE hotel_id = (?)";
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
