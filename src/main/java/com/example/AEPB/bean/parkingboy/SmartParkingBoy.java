package com.example.AEPB.bean.parkingboy;

import com.example.AEPB.bean.parkinglot.ParkingLot;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;

@Slf4j
public class SmartParkingBoy extends ParkingBoy{

    @Override
    ParkingLot findNextAvailableLot() {
        return ParkingLot.lots.stream().max(Comparator.comparingInt(ParkingLot::getPositionNumber)).orElseThrow();
    }
}
