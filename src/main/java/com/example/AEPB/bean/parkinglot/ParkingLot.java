package com.example.AEPB.bean.parkinglot;

import com.example.AEPB.bean.car.Car;

import java.math.BigDecimal;
import java.util.List;

public class ParkingLot {

    public static List<ParkingLot> lots = List.of(
            new ParkingLot(1L, 2),
            new ParkingLot(2L, 5),
            new ParkingLot(3L, 8)
    );

    public static ParkingLot getParkingLotById(Long id) {
        return lots.stream().filter(lot -> lot.getId().equals(id)).findFirst().orElseThrow();
    }

    public static void clean () {
        lots.forEach( parkingLot -> {
            parkingLot.parkingSpace = new Car[parkingLot.max];
        });
    }

    private Long id;
    private int max;
    private Car[] parkingSpace;

    public ParkingLot(Long id, int max) {
        this.id = id;
        this.max = max;
        parkingSpace = new Car[max];
    }

    public int parking(Car car){
        for (int i = 0; i < parkingSpace.length; i++) {
            if (null == parkingSpace[i]){
                parkingSpace[i] = car;
                return i;
            }
        }
        return -1;
    }

    public Car taking(String plate, int index){
        Car car;
        if (null != parkingSpace[index] && parkingSpace[index].getPlate().equals(plate)){
            car = parkingSpace[index];
            parkingSpace[index] = null;
            return car;
        }
        throw new RuntimeException("cat not exist");
    }

    public int getPositionNumber() {
        int number = 0;
        for (Car position: parkingSpace
             ) {
            if (null == position) {
                number ++;
            }
        }
        return number;
    }

    public double getVacancyRate() {
        return Double.valueOf(new BigDecimal(getPositionNumber()).divide(new BigDecimal(max)).toString());
    }


    public Long getId() {
        return id;
    }

}
