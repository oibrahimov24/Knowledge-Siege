package game;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

/**
 * Logger class for logging the in-game events
 */
public class Logger {
	
	/**
	 * Username of the Player whose actions are logges
	 */
    private final String username;
    
    /**
     * Path of the file in which the logging process will go
     */
    private final String logFile;
    
    /**
     * Date and Time for logging
     */
    private final DateTimeFormatter formatter;

    /**
     * Constructor for the Logger object with given username
     * @param username
     */
    public Logger(String username) {
        this.username=username;
        this.logFile=username+".log";
        this.formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * Logs the given event in the file
     * @param event
     */
    public void log(String event) {
        try(FileWriter fileWriter=new FileWriter(logFile, true);
            Formatter writer=new Formatter(fileWriter)) {
            String timestamp=LocalDateTime.now().format(formatter);
            writer.format("[%s] %s%n", timestamp, event);
        } catch(IOException e) {
            System.err.println("Error writing to log file "+logFile+": "+e.getMessage());
        }
    }
}
