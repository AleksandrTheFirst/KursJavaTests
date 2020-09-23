package ru.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    private static double dist;
    public static Point pointA = new Point(3, -65);


    @Test
    public static void isNotZero() {
        dist = pointA.distance(new Point(-5, 34));
        Assert.assertTrue(dist != 0);
    }

    @Test
    public static void isNotLessThenZero() {
        dist = pointA.distance(new Point(5, 8));
        Assert.assertTrue(dist > 0);
    }
}
