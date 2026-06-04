package edu.lawrence.freecycle.Classes;

import java.util.UUID;

public class InterestDTO {

    private UUID itemId;
    private UUID userId;
    private boolean isSelected = false;

    public InterestDTO() {}

    public UUID getItemId() {
        return itemId;
    }

    public void setItemId(UUID itemId) {
        this.itemId = itemId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
