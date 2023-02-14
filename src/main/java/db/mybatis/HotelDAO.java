package db.mybatis;

import db.dao.IHotelDAO;
import db.models.Hotel;
import db.utils.MyBatisSqlFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class HotelDAO implements IHotelDAO {
    private static SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();
    private static final Logger LOGGER = LogManager.getLogger(AirlineCompanyDAO.class);

    @Override
    public void createEntity(Hotel hotel) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IHotelDAO hotelDAO = sqlSession.getMapper(IHotelDAO.class);
            hotelDAO.createEntity(hotel);
            sqlSession.commit();
        }
    }

    @Override
    public void updateEntity(Hotel hotel) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IHotelDAO hotelDAO = sqlSession.getMapper(IHotelDAO.class);
            hotelDAO.updateEntity(hotel);
            sqlSession.commit();
        }
    }

    @Override
    public void removeEntity(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IHotelDAO hotelDAO = sqlSession.getMapper(IHotelDAO.class);
            hotelDAO.removeEntity(id);
            sqlSession.commit();
        }
    }

    @Override
    public Hotel getEntityById(int id) {
        Hotel hotel;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IHotelDAO hotelDAO = sqlSession.getMapper(IHotelDAO.class);
            hotel = hotelDAO.getEntityById(id);
        }
        return hotel;
    }

    @Override
    public List<Hotel> getAllEntities() {
        List<Hotel> hotels;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IHotelDAO hotelDAO = sqlSession.getMapper(IHotelDAO.class);
            hotels = hotelDAO.getAllEntities();
        }
        return hotels;
    }

    @Override
    public Hotel getHotelByName(String name) {
        Hotel hotel;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IHotelDAO hotelDAO = sqlSession.getMapper(IHotelDAO.class);
            hotel = hotelDAO.getHotelByName(name);
        }
        return hotel;
    }

    @Override
    public Hotel getHotelByPrice(double price) {
        Hotel hotel;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IHotelDAO hotelDAO = sqlSession.getMapper(IHotelDAO.class);
            hotel = hotelDAO.getHotelByPrice(price);
        }
        return hotel;
    }
}
