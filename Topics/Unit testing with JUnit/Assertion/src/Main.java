class CalculatorTest {
    void testAddition() {
        // implement a test case
        Calculator calculator = new Calculator();
        int result = calculator.add(1, 2);

        Assertions.assertEquals(3, result);
    }
}
