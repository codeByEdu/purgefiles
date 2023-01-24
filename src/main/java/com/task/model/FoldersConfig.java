package com.task.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FoldersConfig {
    Folders folders;


    // Getter Methods 
   
    public Folders getFolders() {
     return folders;
    }
   
    // Setter Methods 
   
    public void setFolders(Folders folders) {
     this.folders = folders;
    }
}
