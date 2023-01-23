package db.dao.mysql;

import db.connectionpool.ConnectionPool;
import db.dao.ITourDAO;
import db.models.Tour;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TourDAO implements ITourDAO {
    private static final Logger LOGGER = LogManager.getLogger(TourDAO.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public List<Tour> getAllEntities() {
        List<Tour> tourList = new ArrayList<>();
        String sql = "SELECT * FROM tours";
        Connection con = connectionPool.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Tour tour = new Tour();
                tour.setTourId(rs.getInt(1));
                tour.setTourName(rs.getString(2));
                tour.setHotelId(rs.getInt(3));
                tour.setFlightToId(rs.getInt(4));
                tour.setFlightFromId(rs.getInt(5));
                tourList.add(tour);
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
        return tourList;
    }

    public Tour getEntityById(int id) {
        Tour tour = null;
        Connection con = connectionPool.getConnection();
        String sql = "SELECT * FROM tours WHERE tour_id = (?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int tourId = rs.getInt("tour_id");
                String tourName = rs.getString("tour_name");
                int hotelId = rs.getInt("hotel_id");
                int flightToId = rs.getInt("flight_to_id");
                int flightFromId = rs.getInt("flight_from_id");
                tour = new Tour(tourId, tourName, hotelId, flightToId, flightFromId);
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
        return tour;
    }

    public Tour getTourByName(String dbName) {
        Tour tour = null;
        Connection con = connectionPool.getConnection();
        String sql = "SELECT * FROM tours WHERE tour_name = (?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dbName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int tourId = rs.getInt("tour_id");
                String tourName = rs.getString("tour_name");
                int hotelId = rs.getInt("hotel_id");
                int flightToId = rs.getInt("flight_to_id");
                int flightFromId = rs.getInt("flight_from_id");
                tour = new Tour(tourId, tourName, hotelId, flightToId, flightFromId);
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
        return tour;
    }

    public Tour createEntity(Tour tour) {
        Connection con = connectionPool.getConnection();
        String sql = "INSERT INTO tours (tour_id, tour_name, hotel_id, flight_to_id, flight_from_id) " +
                "VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, tour.getTourId());
            ps.setString(2, tour.getTourName());
            ps.setInt(3, tour.getHotelId());
            ps.setInt(4, tour.getFlightToId());
            ps.setInt(5, tour.getFlightFromId());
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

    public void updateEntity(Tour tour) {
        String sql = "UPDATE tours SET tour_name = (?), hotel_id = (?)" +
                "flight_to_id = (?), flight_from_id = (?) WHERE tour_id = (?)";
        Connection con = connectionPool.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            LOGGER.info("in update customer: " + tour);
            ps.setString(1, tour.getTourName());
            ps.setInt(2, tour.getHotelId());
            ps.setInt(3, tour.getFlightToId());
            ps.setInt(4, tour.getFlightFromId());
            ps.setInt(5, tour.getTourId());
            ps.execute();
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

    public void removeEntity(int id) {
        String sql = "Delete FROM tours WHERE tour_id = (?)";
        Connection con = connectionPool.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
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
