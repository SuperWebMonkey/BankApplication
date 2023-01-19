package db.DAO;

import db.models.Staff;

public interface IStaffDAO extends IBaseDAO<Staff> {
    Staff getEntityByFirstName(String first_name);
    Staff getEntityByLastName(String last_name);
}
