package com.task.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Folder")
@XmlAccessorType(XmlAccessType.FIELD)
public class Folder {
    @XmlAttribute
    private String path;
    @XmlAttribute
    private String extensions;
    @XmlAttribute
    private String email;
    @XmlAttribute
    private String name;
    @XmlAttribute
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
