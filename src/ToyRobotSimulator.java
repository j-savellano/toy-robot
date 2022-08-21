import java.util.Scanner;

public class ToyRobotSimulator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String commandStr;
        while (!(commandStr = scanner.nextLine().toUpperCase()).equals("EXIT")) {
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
    static int PLACE_COMMAND_PARAMS_LENGTH = 2;
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
}
