/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author azelv
 */
public class User {

    private String userName;
    private String location;
    private String company;
    private long contributions;
    private String project;

    public User() {
        this.userName="null";
        this.location="null";
        this.company="null";
        this.project="null";
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getContributions() {
        return (int) contributions;
    }

    public void setContributions(int contributions) {
        this.contributions = contributions;
    }

}
