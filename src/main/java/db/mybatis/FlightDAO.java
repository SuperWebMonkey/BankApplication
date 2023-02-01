package db.mybatis;

import db.dao.IFlightDAO;
import db.models.Flight;
import db.utils.MyBatisSqlFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FlightDAO implements IFlightDAO {
    private static SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();
    private static final Logger LOGGER = LogManager.getLogger(AirlineCompanyDAO.class);

    @Override
    public void createEntity(Flight flight) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IFlightDAO flightDAO = sqlSession.getMapper(IFlightDAO.class);
            flightDAO.createEntity(flight);
            sqlSession.commit();
        }
    }

    @Override
    public void updateEntity(Flight flight) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IFlightDAO flightDAO = sqlSession.getMapper(IFlightDAO.class);
            flightDAO.updateEntity(flight);
            sqlSession.commit();
        }
    }

    @Override
    public void removeEntity(int id) {
        try (SqlSession sqlSession =sqlSessionFactory.openSession()) {
            IFlightDAO flightDAO = sqlSession.getMapper(IFlightDAO.class);
            flightDAO.removeEntity(id);
            sqlSession.commit();
        }
    }

    @Override
    public Flight getEntityById(int id) {
        Flight flight;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IFlightDAO flightDAO = sqlSession.getMapper(IFlightDAO.class);
            flight = flightDAO.getEntityById(id);
        }
        return flight;
    }

    @Override
    public List<Flight> getAllEntities() {
        List<Flight> flights;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IFlightDAO flightDAO = sqlSession.getMapper(IFlightDAO.class);
            flights = flightDAO.getAllEntities();
        }
        return flights;
    }

    @Override
    public Flight getFlightByPrice(double price) {
        Flight flight;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IFlightDAO flightDAO = sqlSession.getMapper(IFlightDAO.class);
            flight = flightDAO.getFlightByPrice(price);
        }
        return flight;
    }
}
