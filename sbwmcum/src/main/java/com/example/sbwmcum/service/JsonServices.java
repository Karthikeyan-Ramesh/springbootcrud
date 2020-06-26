package com.example.sbwmcum.service;

import com.example.sbwmcum.pojo.Employee;
import org.json.JSONObject;

public class JsonServices {

    public JSONObject employeeDetailResponse(Employee empObj){
        JSONObject jb = new JSONObject();
        jb.put("id",empObj.getId());
        jb.put("empName",empObj.getEmpName());
        jb.put("gender",empObj.getGender());
        jb.put("mobile",empObj.getMobile());
        return jb;
    }
}
