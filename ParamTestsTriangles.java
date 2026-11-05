import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.stream.Stream;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;

import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import com.triangles.SideException;
import com.triangles.TriangleCalculator;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ParamTestsTriangles {
@ParameterizedTest(name="Площадь треугольника ({0},{1},{2})")
@DisplayName("Площадь треугольника")
@MethodSource("triangleDataProvider")
    public void testAreaTriangles(double a, double b, double c, double expectedArea) throws SideException {
        TriangleCalculator calculator=new TriangleCalculator(a, b, c);
        double actualArea=calculator.getArea();
        assertEquals(expectedArea,actualArea, 0.01);
    }
    private static Stream<Arguments>triangleDataProvider() {
        return Stream.of (
            arguments(3, 4, 5, 6.00),
            arguments(1, 1, 1, 0.43),
            arguments(2.5, 3.5, 5.5, 3.24),
            arguments(100, 100, 100, 4330.13)
        );
    }

@ParameterizedTest(name="Периметр треугольника ({0},{1},{2})")
@DisplayName("Периметр треугольника")
@CsvSource ({
    "3, 4, 5, 12.00",
    "1, 1, 1, 3.00",
    "2.5, 3.5, 5.5, 11.5",
    "100, 100, 100, 300.00"
})
     void testPerimetrTriangls(double a, double b, double c, double expectedPerimetr) throws SideException {
     TriangleCalculator calculator=new TriangleCalculator(a, b, c);
        double actualPerimetr=calculator.getPerimetr();
        assertEquals(expectedPerimetr, actualPerimetr, 0.01);  
     }

@ParameterizedTest(name="Определение типа треугольника ({0}, {1}, {2}-> Тип:{3})")
@DisplayName("Определение типа треугольника")
@CsvSource ({
    "23, 45,56, Разносторонний треугольник. Тупоугольный треугольник.",
    "3, 4, 5, Разносторонний треугольник. Прямоугольный треугольник.",
    "30,30,30, Разноторонний треугольник. Остроугольный треугольник.",
    "3, 5, 9, Треугольник с такими сторонами не существует."
})
     public void testTriangleTypes (double a, double b, double c, String expected) {
        TriangleCalculator calculator=new TriangleCalculator(a, b, c);
        String actualType = calculator.calculate();
        assertEquals(expected, actualType);

     }
}
    
