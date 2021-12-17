package com.example.AEPB.bean.parkingboy;

public class Record {

    private String plant;
    private Long lotId;
    private int position;

    public String getPlant() {
        return plant;
    }

    public Long getLotId() {
        return lotId;
    }


    public int getPosition() {
        return position;
    }

    public Record(String plant, Long lotId, int position) {
        this.plant = plant;
        this.lotId = lotId;
        this.position = position;
    }
}
