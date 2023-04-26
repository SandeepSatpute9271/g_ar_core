package com.google.ar.core.examples.java.utils;/*
 * Created by Sandeep(Techno Learning) on 14,April,2023
 */

import android.content.Context;
import android.text.TextUtils;

import com.google.ar.core.examples.java.models.Employee;
import com.google.ar.core.examples.java.models.EmployeeList;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class DataUtils {

    public static Map<String,String> nameIntroVideoMap = new HashMap<String,String>();

    public static void prepareIntroLinkMappingData(){
        nameIntroVideoMap.clear();
        nameIntroVideoMap.put("Suresh Shelke","https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4");
        nameIntroVideoMap.put("Sam Jean","https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4");
        nameIntroVideoMap.put("Sachin More","https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4");
        nameIntroVideoMap.put("Ramesh Sonwane","https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4");
        nameIntroVideoMap.put("Monika John","https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4");
        nameIntroVideoMap.put("Kiran Kolhe","https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4");
        nameIntroVideoMap.put("Ganesh Gadhave","https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4");
    }

    public static String getIntroLinkVideo(String name){
        String introVideoLink = null;
        if(name!=null && !name.equals("")){
            introVideoLink = nameIntroVideoMap.get(name);
        }
        return introVideoLink;
    }

    public static Employee getEmployeeObject(String employeeName, EmployeeList employeeList){
        Employee employeeObject = null;
        for(Employee employee : employeeList.getEmployeeList()){
            if(!TextUtils.isEmpty(employeeName) &&
                    !TextUtils.isEmpty(employee.getName())){
                    String name = employeeName.split("\\.")[0];
                    if(!TextUtils.isEmpty(name) && name.equalsIgnoreCase(employee.getName())){
                        employeeObject =  employee;
                        break;
                    }
            }
        }
        return employeeObject;
    }

    public static EmployeeList getEmployeeData(Context context) {
        String jsonStrEmpData = loadEmployeeDataFromAsset(context);
        EmployeeList employeeList = new Gson().fromJson(jsonStrEmpData, EmployeeList.class);
        return employeeList;
    }

    public static String loadEmployeeDataFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("employee_list.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
