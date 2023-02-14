package db.patterns;

import db.dao.IBaseDAO;

public interface AbstractFactory {
    IBaseDAO createDAO(String model);
}
