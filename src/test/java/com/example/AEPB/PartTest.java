package com.example.AEPB;

import com.example.AEPB.bean.Car;
import com.example.AEPB.bean.Park;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


public class PartTest {

    @BeforeEach
    public void init() {
        Park.cleanUp();
    }

    @Test
    public void should_enter_failed_when_park_is_full() {
        Park.enter(new Car("111"));
        Park.enter(new Car("222"));
        Assertions.assertFalse(Park.enter(new Car("123")));
    }

    @Test
    public void should_exit_failed_when_car_not_exist() {
        Assertions.assertFalse(Park.exit(new Car("098")));
    }

    @Test
    public void should_enter_success_when_park_is_not_full() {
        Assertions.assertTrue(Park.enter(new Car("123")));
    }

    @Test
    public void should_exit_success_when_car_is_in_park() {
        Car car = new Car("123");
        Park.enter(car);
        Assertions.assertTrue(Park.exit(car));
    }

    @Test
    public void should_return_car_list_when_query_exits() {
        Park.enter(new Car("123"));
        List<String> carList = Park.show();
        Assertions.assertEquals(carList.get(0), "123");
    }
}
