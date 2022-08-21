import java.util.Scanner;

public class ToyRobotSimulator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String commandStr;
        while (!(commandStr = scanner.nextLine().toUpperCase()).equals("EXIT")) {
            if(isPlaceCommand(commandStr)) {
                place();
            } else if(Command.isValid(commandStr)) {
                execute(commandStr);
            } else {
                System.out.printf("Command \"%s\" not supported.%n", commandStr);
            }
        }
    }

    /**
     * Robot placeholder variables
     */
    static int X;
    static int Y;
    static Orientation ORIENTATION;

    static int INIT_X;
    static int INIT_Y;
    static String INIT_ORIENTATION;
    static boolean isPlaced = false;

    /**
     * Constant variables
     */
    static int DIMENSION_X = 5;
    static int DIMENSION_Y = 5;
    static int PLACE_COMMAND_LENGTH = 2;
    static int PLACE_COMMAND_INDEX = 0;
    static int PLACE_COMMAND_PARAMS_INDEX = 1;
    static int PLACE_COMMAND_PARAMS_LENGTH = 3;
    static int PLACE_COMMAND_PARAMS_X_INDEX = 0;
    static int PLACE_COMMAND_PARAMS_Y_INDEX = 1;
    static int PLACE_COMMAND_PARAMS_ORIENTATION_INDEX = 2;

    /**
     * Enums
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
        X = INIT_X;
        Y = INIT_Y;
        ORIENTATION = Orientation.valueOf(INIT_ORIENTATION);
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
                // do nothing;
        }
    }

    static void move() {
        int resX = X;
        int resY = Y;
        switch (ORIENTATION) {
            case NORTH:
                resY = Y + 1;
                break;
            case SOUTH:
                resY = Y - 1;
                break;
            case EAST:
                resX = X + 1;
                break;
            case WEST:
                resX = X - 1;
                break;
            default:
                // do nothing
        }
        if(isCoordinatesValid(resX, resY)) {
            X = resX;
            Y = resY;
        }
    }

    static void left() {
        switch (ORIENTATION) {
            case NORTH:
                ORIENTATION = Orientation.WEST;
                break;
            case SOUTH:
                ORIENTATION = Orientation.EAST;
                break;
            case EAST:
                ORIENTATION = Orientation.NORTH;
                break;
            case WEST:
                ORIENTATION = Orientation.SOUTH;
                break;
            default:
                // do nothing
        }
    }

    static void right() {
        switch (ORIENTATION) {
            case NORTH:
                ORIENTATION = Orientation.EAST;
                break;
            case SOUTH:
                ORIENTATION = Orientation.WEST;
                break;
            case EAST:
                ORIENTATION = Orientation.SOUTH;
                break;
            case WEST:
                ORIENTATION = Orientation.NORTH;
                break;
            default:
                // do nothing
        }
    }

    static void report() {
        System.out.printf("Output: %s,%s,%s%n", X, Y, ORIENTATION);
    }

    /**
     * Utility methods
     */

    static boolean isCoordinatesValid(int x, int y) {
        return x >= 0 && x < DIMENSION_X && y >= 0 && y < DIMENSION_Y;
    }

    static boolean isPlaceCommand(String command) {
        String[] placeCommand = command.split(" ");
        if(placeCommand.length != PLACE_COMMAND_LENGTH) {
            return false;
        }
        return placeCommand[PLACE_COMMAND_INDEX].equals("PLACE")
                && isPlaceCommandParamsValid(placeCommand[PLACE_COMMAND_PARAMS_INDEX]);
    }

    static boolean isPlaceCommandParamsValid(String placeParams) {
        String[] placeCommandParams = placeParams.split(",");
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
}
