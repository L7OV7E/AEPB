package com.example.AEPB.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Park {

    private static List<Car> carPosition = new ArrayList<>();

    private static final int MAX_NUMBER = 2;

    public static boolean enter(Car car){
        if (carPosition.size() == MAX_NUMBER ){
            return false;
        }
        carPosition.add(car);
        return true;
    }

    public static boolean exit(Car car) {
        if (carPosition.contains(car)) {
            carPosition.remove(car);
            return true;
        }
        return false;
    }

    public static void cleanUp() {
        carPosition.clear();
    }

    public static List<String> show() {
        return carPosition.stream().map(Car::getId).collect(Collectors.toList());
    }
}
