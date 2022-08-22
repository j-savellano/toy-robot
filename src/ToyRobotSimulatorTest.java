import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ToyRobotSimulatorTest {

    public static void main(String[] args) {
        integrationTest_scenario1();
        integrationTest_scenario2();
        integrationTest_scenario3();
        integrationTest_scenario4();
        integrationTest_scenario5();
        integrationTest_lowerCaseCommands();
        integrationTest_multiplePlaceCommands();
        integrationTest_mixedValidAndInvalidCommands();
        integrationTest_robotNotPlaced();
        integrationTest_invalidCommands();
        integrationTest_invalidPlaceCommand1();
        integrationTest_invalidPlaceCommand2();

        // Command methods unit tests
        unitTest_place();
        unitTest_moveNorth();
        unitTest_moveSouth();
        unitTest_moveEast();
        unitTest_moveWest();
        unitTest_moveInvalidCoordinates();
        unitTest_leftNorth();
        unitTest_leftWest();
        unitTest_leftSouth();
        unitTest_leftEast();
        unitTest_rightNorth();
        unitTest_rightEast();
        unitTest_rightSouth();
        unitTest_rightWest();
        unitTest_report();
        unitTest_execute();
        unitTest_executeIsPlacedFalse();

        // Utility methods unit tests
        unitTest_validCommand();
        unitTest_invalidCommand();
        unitTest_validOrientation();
        unitTest_invalidOrientation();
        unitTest_validCoordinates();
        unitTest_invalidCoordinates();
        unitTest_validPlaceCommand();
        unitTest_invalidPlaceCommand();
        unitTest_validPlaceCommandParams();
        unitTest_invalidPlaceCommandParams();

        if(isEveryTestPassing) {
            TERMINAL_PRINT_STREAM.println("#####################");
            TERMINAL_PRINT_STREAM.println("# All tests passed. #");
            TERMINAL_PRINT_STREAM.println("#####################");
        } else {
            TERMINAL_PRINT_STREAM.println("####################");
            TERMINAL_PRINT_STREAM.println(String.format("# %d test/s failed. #", failedTestsCount));
            TERMINAL_PRINT_STREAM.println("####################");
        }
    }

    private static PrintStream TERMINAL_PRINT_STREAM = System.out;
    private static ByteArrayOutputStream outputStream;
    private static boolean isEveryTestPassing = true;
    private static int failedTestsCount;
    private static String testName;
    private static String input;
    private static String expectedOutput;

    private static void setupInputOutputStream() {
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    /**
     * Integration tests
     */
    private static void integrationTest_scenario1() {
        testName = "Integration Test - Scenario 1";
        input = String.join(System.lineSeparator(), "PLACE 0,0,NORTH", "MOVE", "REPORT");
        expectedOutput = "Output: 0,1,NORTH\n";

        setupInputOutputStream();
        printTestDetails();

        ToyRobotSimulator.main(new String[0]);

        try {
            assert expectedOutput.equals(outputStream.toString());
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails();
        } finally {
            ToyRobotSimulator.reset();
        }
    }

    private static void integrationTest_scenario2() {
        testName = "Integration Test - Scenario 2";
        input = String.join(System.lineSeparator(), "PLACE 0,0,NORTH", "LEFT", "REPORT");
        expectedOutput = "Output: 0,0,WEST\n";

        setupInputOutputStream();
        printTestDetails();

        ToyRobotSimulator.main(new String[0]);

        try {
            assert expectedOutput.equals(outputStream.toString());
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails();
        } finally {
            ToyRobotSimulator.reset();
        }
    }

    private static void integrationTest_scenario3() {
        testName = "Integration Test - Scenario 3";
        input = String.join(System.lineSeparator(), "PLACE 1,2,EAST", "MOVE", "MOVE", "LEFT", "MOVE", "REPORT");
        expectedOutput = "Output: 3,3,NORTH\n";

        setupInputOutputStream();
        printTestDetails();

        ToyRobotSimulator.main(new String[0]);

        try {
            assert expectedOutput.equals(outputStream.toString());
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails();
        } finally {
            ToyRobotSimulator.reset();
        }
    }

    private static void integrationTest_scenario4() {
        testName = "Integration Test - Scenario 4";
        input = String.join(System.lineSeparator(),
                "PLACE 0,0,NORTH", "MOVE", "MOVE", "MOVE", "MOVE", "REPORT",
                "RIGHT", "MOVE", "MOVE", "MOVE", "MOVE", "REPORT",
                "RIGHT", "MOVE", "MOVE", "MOVE", "MOVE", "REPORT",
                "RIGHT", "MOVE", "MOVE", "MOVE", "MOVE", "REPORT");
        expectedOutput = "Output: 0,4,NORTH\nOutput: 4,4,EAST\nOutput: 4,0,SOUTH\nOutput: 0,0,WEST\n";

        setupInputOutputStream();
        printTestDetails();

        ToyRobotSimulator.main(new String[0]);

        try {
            assert expectedOutput.equals(outputStream.toString());
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails();
        } finally {
            ToyRobotSimulator.reset();
        }
    }

    private static void integrationTest_scenario5() {
        testName = "Integration Test - Scenario 5";
        input = String.join(System.lineSeparator(),
                "PLACE 4,4,SOUTH", "MOVE", "MOVE", "MOVE", "MOVE", "REPORT",
                "RIGHT", "MOVE", "MOVE", "MOVE", "MOVE", "REPORT",
                "RIGHT", "MOVE", "MOVE", "MOVE", "MOVE", "REPORT",
                "RIGHT", "MOVE", "MOVE", "MOVE", "MOVE", "REPORT");
        expectedOutput = "Output: 4,0,SOUTH\nOutput: 0,0,WEST\nOutput: 0,4,NORTH\nOutput: 4,4,EAST\n";

        setupInputOutputStream();
        printTestDetails();

        ToyRobotSimulator.main(new String[0]);

        try {
            assert expectedOutput.equals(outputStream.toString());
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails();
        } finally {
            ToyRobotSimulator.reset();
        }
    }

    private static void integrationTest_lowerCaseCommands() {
        testName = "Integration Test - Lower Case Commands";
        input = String.join(System.lineSeparator(), "place 0,0,NORTH", "left", "report");
        expectedOutput = "Output: 0,0,WEST\n";

        setupInputOutputStream();
        printTestDetails();

        ToyRobotSimulator.main(new String[0]);

        try {
            assert expectedOutput.equals(outputStream.toString());
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails();
        } finally {
            ToyRobotSimulator.reset();
        }
    }

    private static void integrationTest_multiplePlaceCommands() {
        testName = "Integration Test - Multiple Place Commands";
        input = String.join(System.lineSeparator(), "PLACE 0,0,NORTH", "REPORT", "PLACE 4,4,SOUTH", "REPORT", "PLACE 0,4,WEST", "REPORT", "PLACE 4,0,EAST", "REPORT");
        expectedOutput = "Output: 0,0,NORTH\nOutput: 4,4,SOUTH\nOutput: 0,4,WEST\nOutput: 4,0,EAST\n";

        setupInputOutputStream();
        printTestDetails();

        ToyRobotSimulator.main(new String[0]);

        try {
            assert expectedOutput.equals(outputStream.toString());
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails();
        } finally {
            ToyRobotSimulator.reset();
        }
    }

    private static void integrationTest_mixedValidAndInvalidCommands() {
        testName = "Integration Test - Mixed Valid and Invalid Commands";
        input = String.join(System.lineSeparator(), "EUREKA!","PLACE 1,2,EAST", "MOVE", "MOVE", "LEFTT", "LEFT", "MOVE", "MOVEE", "REPORTT", "REPORT");
        expectedOutput = "Invalid \"EUREKA!\" command.\nInvalid \"LEFTT\" command.\nInvalid \"MOVEE\" command.\nInvalid \"REPORTT\" command.\nOutput: 3,3,NORTH\n";

        setupInputOutputStream();
        printTestDetails();

        ToyRobotSimulator.main(new String[0]);

        try {
            assert expectedOutput.equals(outputStream.toString());
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails();
        } finally {
            ToyRobotSimulator.reset();
        }
    }

    private static void integrationTest_robotNotPlaced() {
        testName = "Integration Test - Robot Not Placed";
        input = String.join(System.lineSeparator(), "MOVE", "REPORT");
        expectedOutput = "Robot is not placed. Please place robot.\nRobot is not placed. Please place robot.\n";

        setupInputOutputStream();
        printTestDetails();

        ToyRobotSimulator.main(new String[0]);

        try {
            assert expectedOutput.equals(outputStream.toString());
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails();
        } finally {
            ToyRobotSimulator.reset();
        }
    }

    private static void integrationTest_invalidCommands() {
        testName = "Integration Test - Invalid Commands";
        input = String.join(System.lineSeparator(), "TEST", "INVALID", "COMMAND");
        expectedOutput = "Invalid \"TEST\" command.\nInvalid \"INVALID\" command.\nInvalid \"COMMAND\" command.\n";

        setupInputOutputStream();
        printTestDetails();

        ToyRobotSimulator.main(new String[0]);

        try {
            assert expectedOutput.equals(outputStream.toString());
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails();
        } finally {
            ToyRobotSimulator.reset();
        }
    }

    private static void integrationTest_invalidPlaceCommand1() {
        testName = "Integration Test - Invalid Place Command 1";
        input = String.join(System.lineSeparator(), "PLACE -1,0,SOUTH");
        expectedOutput = "Invalid \"PLACE -1,0,SOUTH\" command.\n";

        setupInputOutputStream();
        printTestDetails();

        ToyRobotSimulator.main(new String[0]);

        try {
            assert expectedOutput.equals(outputStream.toString());
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails();
        } finally {
            ToyRobotSimulator.reset();
        }
    }

    private static void integrationTest_invalidPlaceCommand2() {
        testName = "Integration Test - Invalid Place Command 2";
        input = String.join(System.lineSeparator(), "PLACE X,Y,NORTH");
        expectedOutput = "Invalid \"PLACE X,Y,NORTH\" command.\n";

        setupInputOutputStream();
        printTestDetails();

        ToyRobotSimulator.main(new String[0]);

        try {
            assert expectedOutput.equals(outputStream.toString());
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails();
        } finally {
            ToyRobotSimulator.reset();
        }
    }

    /**
     * Unit tests
     */
    private static void unitTest_place() {
        testName = "Unit Test - Place";
        input = String.format("x = %d, y = %d, orientation = %s", 1, 1, "NORTH");
        expectedOutput = "x = 1, y = 1, orientation = NORTH, isPlaced = true\n";

        printTestDetails();

        ToyRobotSimulator.INIT_X = 1;
        ToyRobotSimulator.INIT_Y = 1;
        ToyRobotSimulator.INIT_ORIENTATION = "NORTH";

        ToyRobotSimulator.place();

        try {
            assert ToyRobotSimulator.ACTUAL_POSITION_X == 1;
            assert ToyRobotSimulator.ACTUAL_POSITION_Y == 1;
            assert ToyRobotSimulator.ACTUAL_ORIENTATION == ToyRobotSimulator.Orientation.NORTH;
            assert ToyRobotSimulator.isPlaced;
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails(String.format("%s execution failed.\n", testName));
        } finally {
            ToyRobotSimulator.reset();
        }
    }

    private static void unitTest_moveNorth() {
        testName = "Unit Test - Move North";
        input = String.format("x = %d, y = %d, orientation = %s", 0, 0, "NORTH");
        expectedOutput = "x = 0, y = 1, orientation = NORTH\n";

        printTestDetails();

        ToyRobotSimulator.ACTUAL_POSITION_X = 0;
        ToyRobotSimulator.ACTUAL_POSITION_Y = 0;
        ToyRobotSimulator.ACTUAL_ORIENTATION = ToyRobotSimulator.Orientation.NORTH;

        ToyRobotSimulator.move();

        try {
            assert ToyRobotSimulator.ACTUAL_POSITION_X == 0;
            assert ToyRobotSimulator.ACTUAL_POSITION_Y == 1;
            assert ToyRobotSimulator.ACTUAL_ORIENTATION == ToyRobotSimulator.Orientation.NORTH;
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails(String.format("%s execution failed.\n", testName));
        } finally {
            ToyRobotSimulator.reset();
        }
    }

    private static void unitTest_moveSouth() {
        testName = "Unit Test - Move South";
        input = String.format("x = %d, y = %d, orientation = %s", 0, 1, "SOUTH");
        expectedOutput = "x = 0, y = 0, orientation = SOUTH\n";

        printTestDetails();

        ToyRobotSimulator.ACTUAL_POSITION_X = 0;
        ToyRobotSimulator.ACTUAL_POSITION_Y = 1;
        ToyRobotSimulator.ACTUAL_ORIENTATION = ToyRobotSimulator.Orientation.SOUTH;

        ToyRobotSimulator.move();

        try {
            assert ToyRobotSimulator.ACTUAL_POSITION_X == 0;
            assert ToyRobotSimulator.ACTUAL_POSITION_Y == 0;
            assert ToyRobotSimulator.ACTUAL_ORIENTATION == ToyRobotSimulator.Orientation.SOUTH;
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails(String.format("%s execution failed.\n", testName));
        } finally {
            ToyRobotSimulator.reset();
        }
    }

    private static void unitTest_moveEast() {
        testName = "Unit Test - Move East";
        input = String.format("x = %d, y = %d, orientation = %s", 0, 0, "EAST");
        expectedOutput = "x = 1, y = 0, orientation = NORTH\n";

        printTestDetails();

        ToyRobotSimulator.ACTUAL_POSITION_X = 0;
        ToyRobotSimulator.ACTUAL_POSITION_Y = 0;
        ToyRobotSimulator.ACTUAL_ORIENTATION = ToyRobotSimulator.Orientation.EAST;

        ToyRobotSimulator.move();

        try {
            assert ToyRobotSimulator.ACTUAL_POSITION_X == 1;
            assert ToyRobotSimulator.ACTUAL_POSITION_Y == 0;
            assert ToyRobotSimulator.ACTUAL_ORIENTATION == ToyRobotSimulator.Orientation.EAST;
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails(String.format("%s execution failed.\n", testName));
        } finally {
            ToyRobotSimulator.reset();
        }
    }

    private static void unitTest_moveWest() {
        testName = "Unit Test - Move West";
        input = String.format("x = %d, y = %d, orientation = %s", 1, 0, "WEST");
        expectedOutput = "x = 0, y = 0, orientation = WEST\n";

        printTestDetails();

        ToyRobotSimulator.ACTUAL_POSITION_X = 1;
        ToyRobotSimulator.ACTUAL_POSITION_Y = 0;
        ToyRobotSimulator.ACTUAL_ORIENTATION = ToyRobotSimulator.Orientation.WEST;

        ToyRobotSimulator.move();

        try {
            assert ToyRobotSimulator.ACTUAL_POSITION_X == 0;
            assert ToyRobotSimulator.ACTUAL_POSITION_Y == 0;
            assert ToyRobotSimulator.ACTUAL_ORIENTATION == ToyRobotSimulator.Orientation.WEST;
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails(String.format("%s execution failed.\n", testName));
        } finally {
            ToyRobotSimulator.reset();
        }
    }

    private static void unitTest_moveInvalidCoordinates() {
        testName = "Unit Test - Move Invalid Coordinates";
        input = String.format("x = %d, y = %d, orientation = %s", 0, 4, "NORTH");
        expectedOutput = "x = 0, y = 0, orientation = WEST\n";

        printTestDetails();

        ToyRobotSimulator.ACTUAL_POSITION_X = 0;
        ToyRobotSimulator.ACTUAL_POSITION_Y = 4;
        ToyRobotSimulator.ACTUAL_ORIENTATION = ToyRobotSimulator.Orientation.NORTH;

        ToyRobotSimulator.move();

        try {
            assert ToyRobotSimulator.ACTUAL_POSITION_X == 0;
            assert ToyRobotSimulator.ACTUAL_POSITION_Y == 4;
            assert ToyRobotSimulator.ACTUAL_ORIENTATION == ToyRobotSimulator.Orientation.NORTH;
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails(String.format("%s execution failed.\n", testName));
        } finally {
            ToyRobotSimulator.reset();
        }
    }

    private static void unitTest_leftNorth() {
        testName = "Unit Test - Left North";
        input = "orientation = NORTH";
        expectedOutput = "orientation = WEST\n";

        printTestDetails();

        ToyRobotSimulator.ACTUAL_ORIENTATION = ToyRobotSimulator.Orientation.NORTH;

        ToyRobotSimulator.left();

        try {
            assert ToyRobotSimulator.ACTUAL_ORIENTATION == ToyRobotSimulator.Orientation.WEST;
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails(String.format("%s execution failed.\n", testName));
        } finally {
            ToyRobotSimulator.reset();
        }
    }

    private static void unitTest_leftWest() {
        testName = "Unit Test - Left West";
        input = "orientation = WEST";
        expectedOutput = "orientation = SOUTH\n";

        printTestDetails();

        ToyRobotSimulator.ACTUAL_ORIENTATION = ToyRobotSimulator.Orientation.WEST;

        ToyRobotSimulator.left();

        try {
            assert ToyRobotSimulator.ACTUAL_ORIENTATION == ToyRobotSimulator.Orientation.SOUTH;
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails(String.format("%s execution failed.\n", testName));
        } finally {
            ToyRobotSimulator.reset();
        }
    }

    private static void unitTest_leftSouth() {
        testName = "Unit Test - Left South";
        input = "orientation = SOUTH";
        expectedOutput = "orientation = EAST\n";

        printTestDetails();

        ToyRobotSimulator.ACTUAL_ORIENTATION = ToyRobotSimulator.Orientation.SOUTH;

        ToyRobotSimulator.left();

        try {
            assert ToyRobotSimulator.ACTUAL_ORIENTATION == ToyRobotSimulator.Orientation.EAST;
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails(String.format("%s execution failed.\n", testName));
        } finally {
            ToyRobotSimulator.reset();
        }
    }

    private static void unitTest_leftEast() {
        testName = "Unit Test - Left East";
        input = "orientation = EAST";
        expectedOutput = "orientation = NORTH\n";

        printTestDetails();

        ToyRobotSimulator.ACTUAL_ORIENTATION = ToyRobotSimulator.Orientation.EAST;

        ToyRobotSimulator.left();

        try {
            assert ToyRobotSimulator.ACTUAL_ORIENTATION == ToyRobotSimulator.Orientation.NORTH;
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails(String.format("%s execution failed.\n", testName));
        } finally {
            ToyRobotSimulator.reset();
        }
    }

    private static void unitTest_rightNorth() {
        testName = "Unit Test - Right North";
        input = "orientation = NORTH";
        expectedOutput = "orientation = EAST\n";

        printTestDetails();

        ToyRobotSimulator.ACTUAL_ORIENTATION = ToyRobotSimulator.Orientation.NORTH;

        ToyRobotSimulator.right();

        try {
            assert ToyRobotSimulator.ACTUAL_ORIENTATION == ToyRobotSimulator.Orientation.EAST;
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails(String.format("%s execution failed.\n", testName));
        } finally {
            ToyRobotSimulator.reset();
        }
    }

    private static void unitTest_rightEast() {
        testName = "Unit Test - Right East";
        input = "orientation = EAST";
        expectedOutput = "orientation = SOUTH\n";

        printTestDetails();

        ToyRobotSimulator.ACTUAL_ORIENTATION = ToyRobotSimulator.Orientation.EAST;

        ToyRobotSimulator.right();

        try {
            assert ToyRobotSimulator.ACTUAL_ORIENTATION == ToyRobotSimulator.Orientation.SOUTH;
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails(String.format("%s execution failed.\n", testName));
        } finally {
            ToyRobotSimulator.reset();
        }
    }

    private static void unitTest_rightSouth() {
        testName = "Unit Test - Right South";
        input = "orientation = SOUTH";
        expectedOutput = "orientation = WEST\n";

        printTestDetails();

        ToyRobotSimulator.ACTUAL_ORIENTATION = ToyRobotSimulator.Orientation.SOUTH;

        ToyRobotSimulator.right();

        try {
            assert ToyRobotSimulator.ACTUAL_ORIENTATION == ToyRobotSimulator.Orientation.WEST;
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails(String.format("%s execution failed.\n", testName));
        } finally {
            ToyRobotSimulator.reset();
        }
    }

    private static void unitTest_rightWest() {
        testName = "Unit Test - Right West";
        input = "orientation = WEST";
        expectedOutput = "orientation = NORTH\n";

        printTestDetails();

        ToyRobotSimulator.ACTUAL_ORIENTATION = ToyRobotSimulator.Orientation.WEST;

        ToyRobotSimulator.right();

        try {
            assert ToyRobotSimulator.ACTUAL_ORIENTATION == ToyRobotSimulator.Orientation.NORTH;
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails(String.format("%s execution failed.\n", testName));
        } finally {
            ToyRobotSimulator.reset();
        }
    }

    private static void unitTest_report() {
        testName = "Unit Test - Report";
        input = "orientation = WEST";
        expectedOutput = "Output: 0,0,WEST\n";

        setupInputOutputStream();
        printTestDetails();

        ToyRobotSimulator.ACTUAL_POSITION_X = 0;
        ToyRobotSimulator.ACTUAL_POSITION_Y = 0;
        ToyRobotSimulator.ACTUAL_ORIENTATION = ToyRobotSimulator.Orientation.WEST;

        ToyRobotSimulator.report();

        try {
            assert expectedOutput.equals(outputStream.toString());
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails();
        } finally {
            ToyRobotSimulator.reset();
        }
    }

    private static void unitTest_execute() {
        testName = "Unit Test - Execute";
        input = "command = RIGHT, orientation = WEST";
        expectedOutput = "orientation = NORTH\n";

        printTestDetails();

        ToyRobotSimulator.ACTUAL_ORIENTATION = ToyRobotSimulator.Orientation.WEST;
        ToyRobotSimulator.isPlaced = true;

        ToyRobotSimulator.execute("RIGHT");

        try {
            assert ToyRobotSimulator.ACTUAL_ORIENTATION == ToyRobotSimulator.Orientation.NORTH;
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails(String.format("%s execution failed.\n", testName));
        } finally {
            ToyRobotSimulator.reset();
        }
    }

    private static void unitTest_executeIsPlacedFalse() {
        testName = "Unit Test - Execute";
        input = "command = RIGHT, orientation = WEST";
        expectedOutput = "Robot is not placed. Please place robot.\n";

        setupInputOutputStream();
        printTestDetails();

        ToyRobotSimulator.ACTUAL_ORIENTATION = ToyRobotSimulator.Orientation.WEST;

        ToyRobotSimulator.execute("RIGHT");

        try {
            assert ToyRobotSimulator.ACTUAL_ORIENTATION == ToyRobotSimulator.Orientation.WEST;
            assert expectedOutput.equals(outputStream.toString());
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails();
        } finally {
            ToyRobotSimulator.reset();
        }
    }

    private static void unitTest_validCoordinates() {
        testName = "Unit Test - Valid Coordinates";
        input = String.format("x = %d, y = %d", 0, 0);
        expectedOutput = "true\n";

        printTestDetails();

        boolean result = ToyRobotSimulator.isCoordinatesValid(0,0);

        try {
            assert result;
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails(result);
        }
    }

    private static void unitTest_invalidCoordinates() {
        testName = "Unit Test - Invalid Coordinates";
        input = String.format("x = %d, y = %d", -1, -1);
        expectedOutput = "false\n";

        printTestDetails();

        boolean result = ToyRobotSimulator.isCoordinatesValid(-1,-1);

        try {
            assert !result;
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails(result);
        }
    }

    private static void unitTest_validPlaceCommand() {
        testName = "Unit Test - Valid Place Command";
        input = "PLACE 0,0,NORTH";
        expectedOutput = "true\n";

        printTestDetails();

        boolean result = ToyRobotSimulator.isPlaceCommand(input);

        try {
            assert result;
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails(result);
        }
    }

    private static void unitTest_invalidPlaceCommand() {
        testName = "Unit Test - Invalid Place Command";
        input = "PLACE X,Y,SOUTHWEST";
        expectedOutput = "false\n";

        printTestDetails();

        boolean result = ToyRobotSimulator.isPlaceCommand(input);

        try {
            assert !result;
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails(result);
        }
    }

    private static void unitTest_validPlaceCommandParams() {
        testName = "Unit Test - Valid Place Command Params";
        input = "0,1,EAST";
        expectedOutput = "true\n";

        printTestDetails();

        boolean result = ToyRobotSimulator.isPlaceCommandParamsValid(input);

        try {
            assert result;
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails(result);
        }
    }
    private static void unitTest_invalidPlaceCommandParams() {
        testName = "Unit Test - Invalid Place Command Params";
        input = "X,Y,SOUTHWEST";
        expectedOutput = "false\n";

        printTestDetails();

        boolean result = ToyRobotSimulator.isPlaceCommandParamsValid(input);

        try {
            assert !result;
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails(result);
        }
    }

    private static void unitTest_validCommand() {
        testName = "Unit Test - Valid Command";
        input = "MOVE";
        expectedOutput = "true\n";

        printTestDetails();

        boolean result = ToyRobotSimulator.Command.isValid(input);

        try {
            assert result;
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails(result);
        }
    }

    private static void unitTest_invalidCommand() {
        testName = "Unit Test - Invalid Command";
        input = "INVALIDCOMMAND";
        expectedOutput = "false\n";

        printTestDetails();

        boolean result = ToyRobotSimulator.Command.isValid(input);

        try {
            assert !result;
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails(result);
        }
    }

    private static void unitTest_validOrientation() {
        testName = "Unit Test - Valid Orientation";
        input = "WEST";
        expectedOutput = "true\n";

        printTestDetails();

        boolean result = ToyRobotSimulator.Orientation.isValid(input);

        try {
            assert result;
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails(result);
        }
    }

    private static void unitTest_invalidOrientation() {
        testName = "Unit Test - Invalid Orientation";
        input = "SOUTHEAST";
        expectedOutput = "false\n";

        printTestDetails();

        boolean result = ToyRobotSimulator.Orientation.isValid(input);

        try {
            assert !result;
        } catch (AssertionError e) {
            failedTestsCount++;
            isEveryTestPassing = false;
            printErrDetails(result);
        }
    }


    /**
     * Test Utility Methods
     */
    private static void printTestDetails() {
        TERMINAL_PRINT_STREAM.println("====================");
        TERMINAL_PRINT_STREAM.println(String.format("Executing %s", testName));
        TERMINAL_PRINT_STREAM.println("Input:");
        TERMINAL_PRINT_STREAM.println(input);
        TERMINAL_PRINT_STREAM.println("Expected Output:");
        TERMINAL_PRINT_STREAM.print(expectedOutput);
    }


    private static void printErrDetails() {
        System.err.println(">>>>>>>>>>>>>>>>>>>>");
        System.err.print(String.format("%s failed.%nExpected:%n%sActual:%n%s", testName, expectedOutput, outputStream));
    }

    private static void printErrDetails(boolean result) {
        System.err.println(">>>>>>>>>>>>>>>>>>>>");
        System.err.print(String.format("%s failed.%nExpected:%n%sActual:%n%s%nPlease check assert expression.%n", testName, expectedOutput, result));
    }

    private static void printErrDetails(String str) {
        System.err.println(">>>>>>>>>>>>>>>>>>>>");
        System.err.print(str);
    }
}
