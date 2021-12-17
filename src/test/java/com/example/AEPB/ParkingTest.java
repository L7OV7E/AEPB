package com.example.AEPB;

import com.example.AEPB.bean.car.Car;
import com.example.AEPB.bean.parkingboy.ParkingBoy;
import com.example.AEPB.bean.parkingboy.ParkingRoboot;
import com.example.AEPB.bean.parkingboy.SmartParkingBoy;
import com.example.AEPB.bean.parkinglot.ParkingLot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingTest {

    @BeforeEach
    public void cleanUp(){
        ParkingLot.clean();
    }

    @Test
    public void shouldParkingInOrderWhenCallACommonBoyAndTakingSuccess() {
        ParkingBoy boy = new ParkingBoy();
        Car car1 = new Car("123");
        Car car2 = new Car("456");
        Car car3 = new Car("789");
        boy.parking(car1);
        boy.parking(car2);
        boy.parking(car3);
        Assertions.assertEquals(boy.getParkingRecord().stream().filter(record -> record.getLotId().equals(1L)).count(), 2);
        Assertions.assertEquals(boy.getParkingRecord().stream().filter(record -> record.getLotId().equals(2L)).count(), 1);
        Assertions.assertEquals(boy.getParkingRecord().stream().filter(record -> record.getLotId().equals(3L)).count(), 0);
        Assertions.assertTrue(boy.taking(car2.getPlate()) == car2);
        Assertions.assertEquals(ParkingLot.getParkingLotById(1L).getPositionNumber(), 1);
    }

    @Test
    public void shouldParkingIntoMostPositionLotWhenCallSmartBoy() {
        ParkingBoy boy = new SmartParkingBoy();
        Car car1 = new Car("123");
        Car car2 = new Car("456");
        Car car3 = new Car("789");
        boy.parking(car1);
        boy.parking(car2);
        boy.parking(car3);
        Assertions.assertEquals(boy.getParkingRecord().stream().filter(record -> record.getLotId().equals(1L)).count(), 0);
        Assertions.assertEquals(boy.getParkingRecord().stream().filter(record -> record.getLotId().equals(2L)).count(), 0);
        Assertions.assertEquals(boy.getParkingRecord().stream().filter(record -> record.getLotId().equals(3L)).count(), 3);
    }

    @Test
    public void shouldParkingIntoMostPositionLotWhenCallRoboot() {
        ParkingBoy boy = new ParkingRoboot();
        Car car1 = new Car("123");
        Car car2 = new Car("234");
        Car car3 = new Car("345");
        Car car4 = new Car("456");
        boy.parking(car1);
        boy.parking(car2);
        boy.parking(car3);
        boy.parking(car4);
        Assertions.assertEquals(boy.getParkingRecord().stream().filter(record -> record.getLotId().equals(1L)).count(), 1);
        Assertions.assertEquals(boy.getParkingRecord().stream().filter(record -> record.getLotId().equals(2L)).count(), 1);
        Assertions.assertEquals(boy.getParkingRecord().stream().filter(record -> record.getLotId().equals(3L)).count(), 2);
    }
}
