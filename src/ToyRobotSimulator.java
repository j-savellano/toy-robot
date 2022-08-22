import java.util.Scanner;

public class ToyRobotSimulator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String commandStr;
        while (scanner.hasNextLine()) {
            commandStr = scanner.nextLine().trim().toUpperCase();
            if(isPlaceCommand(commandStr)) {
                place();
            } else if(Command.isValid(commandStr)) {
                execute(commandStr);
            } else {
                System.out.printf("Invalid \"%s\" command.%n", commandStr);
            }
        }
    }

    /**
     * Robot placeholder variables
     */
    static int ACTUAL_POSITION_X = 0;
    static int ACTUAL_POSITION_Y = 0 ;
    static Orientation ACTUAL_ORIENTATION = null;

    static int INIT_X = 0;
    static int INIT_Y = 0;
    static String INIT_ORIENTATION = null;
    static boolean isPlaced = false;

    /**
     * Constant variables
     */
    static final int DIMENSION_X = 5;
    static final int DIMENSION_Y = 5;

    static final String PLACE_COMMAND = "PLACE";
    static final String PLACE_COMMAND_DELIMITER = " ";
    static final String PLACE_COMMAND_PARAMS_DELIMITER = ",";
    
    static final int PLACE_COMMAND_LENGTH = 2;
    static final int PLACE_COMMAND_INDEX = 0;
    static final int PLACE_COMMAND_PARAMS_INDEX = 1;
    static final int PLACE_COMMAND_PARAMS_LENGTH = 3;
    static final int PLACE_COMMAND_PARAMS_X_INDEX = 0;
    static final int PLACE_COMMAND_PARAMS_Y_INDEX = 1;
    static final int PLACE_COMMAND_PARAMS_ORIENTATION_INDEX = 2;

    /**
     * Enums
     */

    /**
     * Place command not included as it is treated
     * differently from other commands.
     */
    enum Command {
        MOVE,
        LEFT,
        RIGHT,
        REPORT
        ;
        static boolean isValid(String commandStr) {
            for(Command command : Command.values()) {
                if(command.name().equalsIgnoreCase(commandStr)) {
                    return true;
                }
            }
            return false;
        }
    }

    enum Orientation {
        NORTH,
        SOUTH,
        EAST,
        WEST,
        ;
        static boolean isValid(String orientationStr) {
            for(Orientation orientation : Orientation.values()) {
                if(orientation.name().equalsIgnoreCase(orientationStr)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Command methods
     */
    static void place() {
        if(!isPlaced) {
            isPlaced = true;
        }
        ACTUAL_POSITION_X = INIT_X;
        ACTUAL_POSITION_Y = INIT_Y;
        ACTUAL_ORIENTATION = Orientation.valueOf(INIT_ORIENTATION);
    }

    static void execute(String commandStr) {
        if(!isPlaced) {
            System.out.println("Robot is not placed. Please place robot.");
            return;
        }
        Command command = Command.valueOf(commandStr);
        switch (command) {
            case MOVE:
                move();
                break;
            case LEFT:
                left();
                break;
            case RIGHT:
                right();
                break;
            case REPORT:
                report();
                break;
            default:
                // do nothing
        }
    }

    static void move() {
        int tempX = ACTUAL_POSITION_X;
        int tempY = ACTUAL_POSITION_Y;
        switch (ACTUAL_ORIENTATION) {
            case NORTH:
                tempY = ACTUAL_POSITION_Y + 1;
                break;
            case SOUTH:
                tempY = ACTUAL_POSITION_Y - 1;
                break;
            case EAST:
                tempX = ACTUAL_POSITION_X + 1;
                break;
            case WEST:
                tempX = ACTUAL_POSITION_X - 1;
                break;
            default:
                // do nothing
        }
        if(isCoordinatesValid(tempX, tempY)) {
            ACTUAL_POSITION_X = tempX;
            ACTUAL_POSITION_Y = tempY;
        }
    }

    static void left() {
        switch (ACTUAL_ORIENTATION) {
            case NORTH:
                ACTUAL_ORIENTATION = Orientation.WEST;
                break;
            case SOUTH:
                ACTUAL_ORIENTATION = Orientation.EAST;
                break;
            case EAST:
                ACTUAL_ORIENTATION = Orientation.NORTH;
                break;
            case WEST:
                ACTUAL_ORIENTATION = Orientation.SOUTH;
                break;
            default:
                // do nothing
        }
    }

    static void right() {
        switch (ACTUAL_ORIENTATION) {
            case NORTH:
                ACTUAL_ORIENTATION = Orientation.EAST;
                break;
            case SOUTH:
                ACTUAL_ORIENTATION = Orientation.WEST;
                break;
            case EAST:
                ACTUAL_ORIENTATION = Orientation.SOUTH;
                break;
            case WEST:
                ACTUAL_ORIENTATION = Orientation.NORTH;
                break;
            default:
                // do nothing
        }
    }

    static void report() {
        System.out.printf("Output: %s,%s,%s%n", ACTUAL_POSITION_X, ACTUAL_POSITION_Y, ACTUAL_ORIENTATION);
    }

    /**
     * Utility methods
     */
    static boolean isCoordinatesValid(int x, int y) {
        return x >= 0 && x < DIMENSION_X && y >= 0 && y < DIMENSION_Y;
    }

    static boolean isPlaceCommand(String command) {
        String[] placeCommand = command.split(PLACE_COMMAND_DELIMITER);
        if(placeCommand.length != PLACE_COMMAND_LENGTH) {
            return false;
        }
        return PLACE_COMMAND.equals(placeCommand[PLACE_COMMAND_INDEX])
                && isPlaceCommandParamsValid(placeCommand[PLACE_COMMAND_PARAMS_INDEX]);
    }

    static boolean isPlaceCommandParamsValid(String placeParams) {
        String[] placeCommandParams = placeParams.split(PLACE_COMMAND_PARAMS_DELIMITER);
        if(placeCommandParams.length != PLACE_COMMAND_PARAMS_LENGTH) {
            return false;
        }
        try {
            INIT_X = Integer.parseInt(placeCommandParams[PLACE_COMMAND_PARAMS_X_INDEX]);
            INIT_Y = Integer.parseInt(placeCommandParams[PLACE_COMMAND_PARAMS_Y_INDEX]);
        } catch (NumberFormatException e) {
            return false;
        }
        INIT_ORIENTATION = placeCommandParams[PLACE_COMMAND_PARAMS_ORIENTATION_INDEX];
        return Orientation.isValid(INIT_ORIENTATION) && isCoordinatesValid(INIT_X, INIT_Y);
    }

    /**
     * Resets all robot placeholder variables.
     * Utility method used in tests
     */
    static void reset() {
        ACTUAL_POSITION_X = 0;
        ACTUAL_POSITION_Y = 0 ;
        ACTUAL_ORIENTATION = null;

        INIT_X = 0;
        INIT_Y = 0;
        INIT_ORIENTATION = null;

        isPlaced = false;
    }
}
