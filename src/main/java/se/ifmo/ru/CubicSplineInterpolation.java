package se.ifmo.ru;

import java.util.ArrayList;
import java.util.List;

public class CubicSplineInterpolation {

    private SplineCoefficients[] splineCoefficients;

    public void createSplines(double[] xArray, double[] yArray, int n) {

        splineCoefficients = new SplineCoefficients[n];

        for (int i = 0; i < n; i++) {
            splineCoefficients[i].setX(xArray[i]);
            splineCoefficients[i].setA(yArray[i]);
        }

        splineCoefficients[0].setC(0);
        splineCoefficients[n-1].setC(0);

        double[] alpha = new double[n - 1];
        double[] beta = new double[n - 1];

        double hi, hi1, a, b, c, f;

        for (int i = 0; i < n; i++) {
            hi = xArray[i] - xArray[i - 1];
            hi1 = xArray[i + 1] - xArray[i];
            a = hi;
            b = hi1;
            c = 2 * (hi + hi1);
            f = 6 * ((yArray[i + 1] - yArray[i]) / hi1 - (yArray[i] - yArray[i - 1]) / hi);

            alpha[i] = -b / (a * alpha[i - 1] + c);
            beta[i] = (f - a * beta[i - 1] / (a * alpha[i - 1] + c));
        }
    }
}
