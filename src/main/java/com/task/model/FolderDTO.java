package com.task.model;

public class FolderDTO {
    private String path;
    private String extensions;
    private String email;
    private String name; 
    private Integer timeToKeepFiles;


    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getExtensions() {
        return extensions;
    }
    public void setExtensions(String extensions) {
        this.extensions = extensions;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getTimeToKeepFiles() {
        return timeToKeepFiles;
    }
    public void setTimeToKeepFiles(Integer timeToKeepFiles) {
        this.timeToKeepFiles = timeToKeepFiles;
    }

    
    
}
