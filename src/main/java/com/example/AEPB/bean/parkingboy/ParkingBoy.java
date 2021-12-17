package com.example.AEPB.bean.parkingboy;

import com.example.AEPB.bean.car.Car;
import com.example.AEPB.bean.parkinglot.ParkingLot;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ParkingBoy {

    private List<Record> parkingRecord = new ArrayList<>();

    public boolean parking(Car car) {
        ParkingLot nextAvailableLot = findNextAvailableLot();
        int position = nextAvailableLot.parking(car);
        if (-1 != position) {
            addRecord(car, nextAvailableLot, position);
            return true;
        }
        return false;
    }

    private void addRecord(Car car, ParkingLot nextAvailableLot, int position) {
        parkingRecord.add(new Record(car.getPlate(), nextAvailableLot.getId(), position));
    }

    ParkingLot findNextAvailableLot() {
        return ParkingLot.lots.stream().filter(lot -> lot.getPositionNumber() > 0).findFirst().orElseThrow();
    }

    public Car taking(String plant) {
        Record record = parkingRecord.stream().filter(re -> re.getPlant().equals(plant)).findAny().orElseThrow();
        Car car =  ParkingLot.getParkingLotById(record.getLotId()).taking(record.getPlant(), record.getPosition());
        parkingRecord.remove(record);
        return car;
    }

    public List<Record> getParkingRecord() {
        return parkingRecord;
    }
}
