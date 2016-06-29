package getname.group.project_4.debug;

import android.util.Log;

import getname.group.project_4.activities.ActivityExtender;

/**
 * Created by Lucas on 6/29/2016.
 */
public class LogHelper {

    private static void printLog(String tag, String message, int logType) {
        switch (logType) {
            case Log.DEBUG:
                Log.d(tag, message);
                break;
            case Log.ERROR:
                Log.e(tag, message);
                break;
            case Log.INFO:
                Log.i(tag, message);
                break;
            case Log.VERBOSE:
                Log.v(tag, message);
                break;
            case Log.WARN:
                Log.w(tag, message);
                break;
            default:
                Log.d(tag, message);
                break;
        }
    }

    /**
     *
     * @param context
     * @param message
     */
    public static void logDebugMessage(String context, String message) {
        String[] logString = new String[] {
                "["+context.toUpperCase()+"]",
                message
        };

        printLog(logString[0], logString[1], Log.ERROR);
    }

    /**
     *
     * @param context
     * @param message
     * @param logType
     */
    public static void logDebugMessage(String context, String message, int logType) {
        String[] logString = new String[] {
                "["+context.toUpperCase()+"]",
                message
        };

        printLog(logString[0], logString[1], logType);
    }

    /**
     *  Builds and prints debug message.
     *  Defaults logType to Log.ERROR for easy readability
     * @param context
     * @param activityInstance
     */
    public static void logDebugMessage(String context, ActivityExtender activityInstance) {
        String[] logString = new String[] {
                "["+context.toUpperCase()+"]",
                activityInstance.getClass().getSimpleName() + " " + activityInstance.getID()
        };

        printLog(logString[0], logString[1], Log.ERROR);
    }

    /**
     * Builds and prints Log message.
     * @param context
     * @param activityInstance
     * @param logType
     */
    public static void logDebugMessage(String context, ActivityExtender activityInstance, int logType) {
        String[] logString = new String[] {
                "["+context.toUpperCase()+"]",
                activityInstance.getClass().getSimpleName() + " " + activityInstance.getID()
        };

        printLog(logString[0], logString[1], logType);
    }
}
