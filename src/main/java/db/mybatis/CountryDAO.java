package db.mybatis;

import db.dao.ICityDAO;
import db.dao.ICountryDAO;
import db.models.City;
import db.models.Country;
import db.utils.MyBatisSqlFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CountryDAO implements ICountryDAO {
    private static SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();
    private static final Logger LOGGER = LogManager.getLogger(AirlineCompanyDAO.class);

    @Override
    public void createEntity(Country country) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICountryDAO countryDAO = sqlSession.getMapper(ICountryDAO.class);
            countryDAO.createEntity(country);
            sqlSession.commit();
        }
    }

    @Override
    public void updateEntity(Country country) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICountryDAO countryDAO = sqlSession.getMapper(ICountryDAO.class);
            countryDAO.updateEntity(country);
            sqlSession.commit();
        }
    }

    @Override
    public void removeEntity(int id) {
        try (SqlSession sqlSession =sqlSessionFactory.openSession()) {
            ICountryDAO countryDAO = sqlSession.getMapper(ICountryDAO.class);
            countryDAO.removeEntity(id);
            sqlSession.commit();
        }
    }

    @Override
    public Country getEntityById(int id) {
        Country country;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICountryDAO countryDAO = sqlSession.getMapper(ICountryDAO.class);
            country = countryDAO.getEntityById(id);
        }
        return country;
    }

    @Override
    public List<Country> getAllEntities() {
        List<Country> countries;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICountryDAO countryDAO = sqlSession.getMapper(ICountryDAO.class);
            countries = countryDAO.getAllEntities();
        }
        return countries;
    }

    @Override
    public Country getCountryByName(String name) {
        Country country;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICountryDAO countryDAO = sqlSession.getMapper(ICountryDAO.class);
            country = countryDAO.getCountryByName(name);
        }
        return country;
    }
}
