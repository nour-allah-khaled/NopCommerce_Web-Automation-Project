package com.nopcommerce.utils.logs;

import com.nopcommerce.report.AllureConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogsManager {
    // Create one shared logger instance
    public static final String LOGS_PATH = AllureConstants.USER_DIR + "/Test-out/Logs/";
    private static  Logger logger (){
        return  LogManager.getLogger(Thread.currentThread().getStackTrace()[3].getClassName());
    }

    public static void trace(String... message) {
        logger().trace(String.join(" ", message));
    }

    public static void debug(String... message) {
        logger().debug(String.join(" ", message));
    }

    public static void info(String... message) {
        logger().info(String.join(" ", message));
    }

    public static void warn(String... message) {
        logger().warn(String.join(" ", message));
    }

    public static void error(String... message) {
        logger().error(String.join(" ", message));
    }

    public static void fatal(String... message) {
        logger().fatal(String.join(" ", message));
    }
}
