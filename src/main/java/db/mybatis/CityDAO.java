package db.mybatis;

import db.dao.IAirlineCompanyDAO;
import db.dao.ICityDAO;
import db.models.AirlineCompany;
import db.models.City;
import db.utils.MyBatisSqlFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CityDAO implements ICityDAO {
    private static SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();
    private static final Logger LOGGER = LogManager.getLogger(AirlineCompanyDAO.class);

    @Override
    public void createEntity(City city) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICityDAO cityDAO = sqlSession.getMapper(ICityDAO.class);
            cityDAO.createEntity(city);
            sqlSession.commit();
        }
    }

    @Override
    public void updateEntity(City city) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICityDAO cityDAO = sqlSession.getMapper(ICityDAO.class);
            cityDAO.updateEntity(city);
            sqlSession.commit();
        }
    }

    @Override
    public void removeEntity(int id) {
        try (SqlSession sqlSession =sqlSessionFactory.openSession()) {
            ICityDAO cityDAO = sqlSession.getMapper(ICityDAO.class);
            cityDAO.removeEntity(id);
            sqlSession.commit();
        }
    }

    @Override
    public City getEntityById(int id) {
        City city;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICityDAO cityDAO = sqlSession.getMapper(ICityDAO.class);
            city = cityDAO.getEntityById(id);
        }
        return city;
    }

    @Override
    public List<City> getAllEntities() {
        List<City> cities;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICityDAO cityDAO = sqlSession.getMapper(ICityDAO.class);
            cities = cityDAO.getAllEntities();
        }
        return cities;
    }

    @Override
    public City getCityByName(String name) {
        City city;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICityDAO cityDAO = sqlSession.getMapper(ICityDAO.class);
            city = cityDAO.getCityByName(name);
        }
        return city;
    }
}
