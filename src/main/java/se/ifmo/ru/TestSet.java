package se.ifmo.ru;

import java.util.ArrayList;
import java.util.List;

public enum TestSet {

    FIRST, SECOND, THIRD;

    public List<Double> setXTestSet() {
        List<Double> xList = new ArrayList<Double>();
        switch (this) {
            case FIRST:
                xList.add(0.0);
                xList.add(Math.PI / 2);
                xList.add(Math.PI);
                xList.add(3 * Math.PI / 2);
                xList.add(2 * Math.PI);
                return xList;
            case SECOND:
            case THIRD:
                xList.add(0.0);
                xList.add(Math.PI / 4);
                xList.add(Math.PI / 2);
                xList.add(3 * Math.PI / 4);
                xList.add(Math.PI);
                xList.add(5 * Math.PI / 4);
                xList.add(3 * Math.PI / 2);
                xList.add(7 * Math.PI / 4);
                xList.add(2 * Math.PI);
                return xList;
            default:
                return xList;
        }

    }

    public List<Double> setYTestSet() {
        List<Double> yList = new ArrayList<Double>();
        switch (this) {
            case FIRST:
                yList.add(0.0);
                yList.add(1.0);
                yList.add(0.0);
                yList.add(-1.0);
                yList.add(0.0);
                return yList;
            case SECOND:
                yList.add(Math.sin(0.0));
                yList.add(Math.sin(Math.PI / 4));
                yList.add(Math.sin(Math.PI / 2));
                yList.add(Math.sin(3 * Math.PI / 4));
                yList.add(Math.sin(Math.PI));
                yList.add(Math.sin(5 * Math.PI / 4));
                yList.add(Math.sin(3 * Math.PI / 2));
                yList.add(Math.sin(7 * Math.PI / 4));
                yList.add(Math.sin(2 * Math.PI));
                return yList;
            case THIRD:
                yList.add(Math.sin(0.0));
                yList.add(Math.sin(Math.PI / 4));
                yList.add(Math.sin(Math.PI / 2));
                yList.add(-6.0);
                yList.add(Math.sin(Math.PI));
                yList.add(Math.sin(5 * Math.PI / 4));
                yList.add(Math.sin(3 * Math.PI / 2));
                yList.add(Math.sin(7 * Math.PI / 4));
                yList.add(Math.sin(2 * Math.PI));
                return yList;
            default:
                return yList;
        }

//    public List<Double> setXTestSet() {
//        List<Double> xList = new ArrayList<Double>();
//        switch (this) {
//            case FIRST:
//                xList.add(0.0);
//                xList.add(Math.PI / 2);
//                xList.add(Math.PI);
//                xList.add(3 * Math.PI / 2);
//                xList.add(2 * Math.PI);
//                return xList;
//            case SECOND:
//            case THIRD:
//                xList.add(0.0);
//                xList.add(Math.PI / 4);
//                xList.add(Math.PI / 2);
//                xList.add(3 * Math.PI / 4);
//                xList.add(Math.PI);
//                xList.add(5 * Math.PI / 4);
//                xList.add(3 * Math.PI / 2);
//                xList.add(7 * Math.PI / 4);
//                xList.add(2 * Math.PI);
//                return xList;
//            default:
//                return xList;
//        }
//
//    }
//
//    public List<Double> setYTestSet() {
//        List<Double> yList = new ArrayList<Double>();
//        switch (this) {
//            case FIRST:
//                yList.add(0.0);
//                yList.add(1.0);
//                yList.add(0.0);
//                yList.add(-1.0);
//                yList.add(0.0);
//                return yList;
//            case SECOND:
//                yList.add(Math.pow(0.0, 2));
//                yList.add(Math.pow(Math.PI / 4, 2));
//                yList.add(Math.pow(Math.PI / 2, 2));
//                yList.add(Math.pow(3 * Math.PI / 4, 2));
//                yList.add(Math.pow(Math.PI, 2));
//                yList.add(Math.pow(5 * Math.PI / 4, 2));
//                yList.add(Math.pow(3 * Math.PI / 2, 2));
//                yList.add(Math.pow(7 * Math.PI / 4, 2));
//                yList.add(Math.pow(2 * Math.PI, 2));
//                return yList;
//            case THIRD:
//                yList.add(Math.pow(0.0, 2));
//                yList.add(Math.pow(Math.PI / 4, 2));
//                yList.add(Math.pow(Math.PI / 2, 2));
//                yList.add(-6.0);
//                yList.add(Math.pow(Math.PI, 2));
//                yList.add(Math.pow(5 * Math.PI / 4, 2));
//                yList.add(Math.pow(3 * Math.PI / 2, 2));
//                yList.add(Math.pow(7 * Math.PI / 4, 2));
//                yList.add(Math.pow(2 * Math.PI, 2));
//                return yList;
//            default:
//                return yList;
//        }

    }
}
