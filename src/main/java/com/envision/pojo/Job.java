package com.envision.pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Job {

    private String name;

    private String job;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public static void main(String[] args) throws JsonProcessingException {
        Job jobs = new Job();
        jobs.setJob("zion resident");
        jobs.setName("Tarun Sachdeva");

        Job jobs1 = new Job();
        jobs1.setJob("zion resident");
        jobs1.setName("Tarun Sachdeva");

        List<Job> jsonArray = new ArrayList<>();
        jsonArray.add(jobs);
        jsonArray.add(jobs1);
        JSONObject jobsJson = new JSONObject(jobs);
        System.out.println(jobsJson);
        ObjectMapper objectMapper = new ObjectMapper();
        Job jobObj =objectMapper.readValue(jobsJson.toString(),Job.class);
        System.out.println(jobObj.getJob()+ " : "+ jobObj.getName());

        JSONArray jobsJsonArr = new JSONArray(jsonArray);
        System.out.println(jobsJsonArr);


    }
}
