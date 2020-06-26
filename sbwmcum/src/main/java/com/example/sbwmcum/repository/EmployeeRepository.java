package com.example.sbwmcum.repository;

import com.example.sbwmcum.pojo.Employee;
import com.mongodb.WriteResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.morphia.Datastore;
import xyz.morphia.FindAndModifyOptions;
import xyz.morphia.Key;
import xyz.morphia.query.Query;
import xyz.morphia.query.UpdateOperations;
import xyz.morphia.query.UpdateResults;

@Repository
public class EmployeeRepository {

    @Autowired
    private Datastore datastore;

    public Key<Employee> save(Employee empObj) {
        return datastore.save(empObj);
    }

    public Employee readById(ObjectId id) {
        return datastore.get(Employee.class,id);
    }

    public WriteResult delete(Employee empObj) {
        return datastore.delete(empObj);
    }

    public Employee updateById(Employee empObj,ObjectId id) {
        Query<Employee> query = datastore.createQuery(Employee.class);
        query.filter("_id", id);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        UpdateOperations<Employee> update = datastore.createUpdateOperations(Employee.class)
                .set("empName",empObj.getEmpName())
                .set("gender",empObj.getGender())
                .set("mobile",empObj.getMobile());
        return datastore.findAndModify(query, update, options);
    }
}
