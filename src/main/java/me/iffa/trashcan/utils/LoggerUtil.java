// Package Declaration
package me.iffa.trashcan.utils;

// Java Imports
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class to make logging using Logger easier.
 * 
 * @author iffamies
 */
public class LoggerUtil {
    // Variables
    private static final Logger log = Logger.getLogger("Minecraft");
    
    /**
     * Logs a message using Logger.
     * 
     * @param level Logging level
     * @param message Message
     */
    public static void log(Level level, String message) {
        log.log(level, message);
    }

    /**
     * Constructor of LoggerUtil.
     */
    private LoggerUtil() {
    }
}
