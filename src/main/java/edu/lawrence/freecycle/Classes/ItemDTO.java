package edu.lawrence.freecycle.Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.ElementCollection;

public class ItemDTO {

    private String name;
    private String description;
    private String status;
    private UUID donorId;

    @ElementCollection
    private List<String> tags = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setItemName(String name) {
        this.name = name;
    }

    public UUID getDonorId() {
        return donorId;
    }

    public void setDonorId(UUID donorId) {
        this.donorId = donorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

}
