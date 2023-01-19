package db.dao.mysql;

import db.ConnectionPool.ConnectionPool;
import db.dao.IFlightDAO;
import db.models.Flight;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FlightDao implements IFlightDAO {
    private static final Logger LOGGER = LogManager.getLogger(FlightDao.class);

    public List<Flight> getAllEntities(){
        List<Flight>  flightList = new ArrayList<>();
        String sql = "SELECT * FROM flights";
        Connection con = ConnectionPool.getInstance().getConnection();

        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Flight flight = new Flight();
                flight.setFlightId(rs.getInt(1));
                flight.setPrice(rs.getDouble(2));
                flight.setAirlineId(rs.getInt(3));
                flight.setOriginCityId(rs.getInt(4));
                flight.setDestinationCityId(rs.getInt(5));

                flightList.add(flight);
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

        return flightList;
    }

    public Flight getEntityById(int id) {
        Flight flight = null;
        Connection con = ConnectionPool.getInstance().getConnection();
        String sql = "SELECT * FROM flights WHERE flight_id = (?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int flightId = rs.getInt("flight_id");
                double price = rs.getDouble("price");
                int airlineId = rs.getInt("airline_id");
                int originCityId = rs.getInt("origin_city_id");
                int destinationCityId = rs.getInt("destination_city_id");

                flight = new Flight(flightId, price, airlineId, originCityId, destinationCityId);
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

        return flight;
    }

    public Flight createEntity(Flight flight) {
        Connection con = ConnectionPool.getInstance().getConnection();
        String sql = "INSERT INTO flights (flight_id, price, airline_id, origin_city_id, destination_city_id) VALUES (?,?,?,?,?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, flight.getFlightId());
            ps.setDouble(2, flight.getPrice());
            ps.setInt(3, flight.getAirlineId());
            ps.setInt(4, flight.getOriginCityId());
            ps.setInt(5, flight.getDestinationCityId());
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

    public void updateEntity(Flight flight) {
        String sql = "UPDATE flights SET price = (?), airline_id = (?), origin_city_id = (?), " +
                     "destination_city_id = (?) WHERE flight_id = (?)";
        Connection con = ConnectionPool.getInstance().getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            LOGGER.info("in update customer: " + flight);

            ps.setDouble(1, flight.getPrice());
            ps.setInt(2, flight.getAirlineId());
            ps.setInt(3, flight.getOriginCityId());
            ps.setInt(4, flight.getDestinationCityId());
            ps.setInt(5, flight.getFlightId());
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
        String sql = "Delete FROM flights WHERE flight_id = (?)";
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
