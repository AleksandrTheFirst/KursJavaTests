package ru.sandbox;

public class Program {

    public static void main(String[] args) {

    Point pointA = new Point(3, -4);
    Point pointB = new Point(8, 7);
    System.out.println("Расстояние между точкой A и точкой B равно " + distance(pointA, pointB));
    System.out.println("Расстояние между точкой A и точкой B равно " + pointA.distance(pointB));

    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }

 }