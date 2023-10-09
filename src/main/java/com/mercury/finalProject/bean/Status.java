package com.mercury.finalProject.bean;

public enum Status {

    PENDING_DELIVERY(0),
    COMPLETED(1),
    CANCELED(2),
    UNKNOWN(3);

    Status(String status){
        if (status.equals("CANCELED")){
             this.statusValue = 2;
        } else if (status.equals("COMPLETED")){
            this.statusValue = 1;
        } else if (status.equals("PENDING_DELIVERY")){
            this.statusValue = 0;
        }
    }

    private int statusValue;

    Status(int statusValue) {
        this.statusValue = statusValue;
    }

    public int getStatusId() {
        return statusValue;
    }

    public static Status fromValue(String status) {
        if (status.equals("CANCELED")){
            return Status.CANCELED;
        } else if (status.equals("COMPLETED")){
            return Status.COMPLETED;
        } else if (status.equals("PENDING_DELIVERY")){
            return Status.PENDING_DELIVERY;
        }
        return Status.UNKNOWN;
    }
}
