package db.dao;

import db.models.Staff;

public interface IStaffDAO extends IBaseDAO<Staff> {
    Staff getStaffByFirstName(String first_name);
    Staff getStaffByLastName(String last_name);
}
