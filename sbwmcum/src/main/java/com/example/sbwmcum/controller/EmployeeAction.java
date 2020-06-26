package com.example.sbwmcum.controller;

import com.example.sbwmcum.pojo.Employee;
import com.example.sbwmcum.pojo.EmployeeApiResponse;
import com.example.sbwmcum.repository.EmployeeRepository;
import com.example.sbwmcum.service.JsonServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.morphia.Datastore;
import xyz.morphia.Key;

@RestController
@RequestMapping("/manage/employee")
public class EmployeeAction {

    @Autowired
    @Qualifier("emp_details")
    private Datastore datastore;

    @Autowired
    private EmployeeRepository empRepo;

    @Autowired
    private JsonServices jsonObj;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<EmployeeApiResponse> createEmployee(@RequestBody Employee empObj) {
        EmployeeApiResponse s = new EmployeeApiResponse();
        if(empObj.getEmpName()!=null || empObj.getEmpName().length()!=0 ){

            if(empObj.getMobile().length() == 10){
                Key<Employee> emp = empRepo.save(empObj);
                s.setStatus("Success");
                s.setMessage("Employee detail was created successfully");
            }else{
                s.setStatus("Creation failed !");
                s.setMessage("Mobile Number should be in 10 digit !");
            }
        }else{
            s.setStatus("Creation failed !");
            s.setMessage("Please Enter employee name !");
        }
        return new ResponseEntity<EmployeeApiResponse>(s, HttpStatus.OK);
    }

    @GetMapping(value ="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<EmployeeApiResponse> getEmployeeByKey(@PathVariable("id") ObjectId id){
        Employee resultObj = empRepo.readById(id);
        EmployeeApiResponse s = new EmployeeApiResponse();
        if(resultObj == null){
            s.setStatus("Fetching failed !");
            s.setMessage("Object id doesn't exists !");
        }
        s.setStatus("Success");
        s.setData(jsonObj.employeeDetailResponse(resultObj));
        s.setMessage("Successfully fetched the employee details");
        return new ResponseEntity<EmployeeApiResponse>(s, HttpStatus.OK);
    }

    @DeleteMapping(value ="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<EmployeeApiResponse> deleteEmployeeByKey(@PathVariable("id") ObjectId id){
        Employee empObj = empRepo.readById(id);
        EmployeeApiResponse s = new EmployeeApiResponse();
        if(empObj == null){
            s.setStatus("Deletion failed !");
            s.setMessage("Object id doesn't exists !");
        }
        empRepo.delete(empObj);
        s.setStatus("Success");
        s.setMessage("Employee detail was deleted successfully");
        return new ResponseEntity<EmployeeApiResponse>(s, HttpStatus.OK);
    }

    @PutMapping(value="/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<EmployeeApiResponse> updateEmployeeByKey(@PathVariable("id") ObjectId id,@RequestBody Employee empObj) {
        EmployeeApiResponse s = new EmployeeApiResponse();
        if(empObj.getEmpName()!=null || empObj.getEmpName().length()!=0 ){
            if(empObj.getMobile().length() == 10){
                Employee resultObj = empRepo.updateById(empObj,id);
                s.setStatus("Success");
                s.setData(jsonObj.employeeDetailResponse(resultObj));
                s.setMessage("Employee detail was updated successfully");
            }else{
                s.setStatus("Updating failed !");
                s.setMessage("Mobile Number should be in 10 digit !");
            }
        }else{
            s.setStatus("Updating failed !");
            s.setMessage("Please Enter employee name !");
        }
        return new ResponseEntity<EmployeeApiResponse>(s, HttpStatus.OK);
    }

}
