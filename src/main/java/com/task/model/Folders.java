package com.task.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Folders")
@XmlAccessorType(XmlAccessType.FIELD)
public class Folders {
    @XmlElement
    private List<Folder> Folder;

    public List<Folder> getFolder() {
        return Folder;
    }

    public void setFolder(List<Folder> Folder) {
        this.Folder = Folder;
    }

    
}
