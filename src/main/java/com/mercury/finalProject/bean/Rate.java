//package com.mercury.finalProject.bean;
//
//public enum Rate {
//
//    ONE_STAR(0),
//    TWO_STARS(1),
//    THREE_STARS(2),
//    FOUR_STARS(3),
//    FIVE_STARS(4);
//
//    private final int rateValue;
//
//    Rate(int rateValue) {
//        this.rateValue = rateValue;
//    }
//
//    public int getRateValue() {
//        return rateValue;
//    }
//
//    public static Rate fromValue(int rateValue) {
//        for (Rate rate : Rate.values()) {
//            if (rate.rateValue == rateValue) {
//                return rate;
//            }
//        }
//        throw new IllegalArgumentException("Invalid rating value: " + rateValue);
//    }
//
//}
