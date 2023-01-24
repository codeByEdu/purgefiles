package com.task.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="folders")
public class FoldersDTO {
    private List<FolderDTO> folder;

    public List<FolderDTO> getFolder() {
        return folder;
    }

    public void setFolder(List<FolderDTO> folder) {
        this.folder = folder;
    }

    
}
