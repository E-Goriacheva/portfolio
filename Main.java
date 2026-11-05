package com.triangles;


public class Main {
    public static void main(String[] args) throws SideException {
        double sideA = 2.5;
        double sideB = 3.5;
        double sideC = 5.5;
        // Создаем объект calculator класса TriangleCalculator и выполняем расчеты
        TriangleCalculator calculator = new TriangleCalculator(sideA,sideB, sideC);
        String result = calculator.calculate();
        System.out.println(result);
        System.out.println("Площадь = "+calculator.getArea());
        System.out.println("Периметр = "+calculator.getPerimetr());
    }
}