import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>FirstLogging class</h1> It isn't used in the program, it was just a good logging practice.
 */
public class FirstLogging {
    private static final Logger logger = LoggerFactory.getLogger(FirstLogging.class);
    private int temperature;

    /**
     * Sets temperature with the given integer.
     *
     * @param temperature temperature
     */
    public void setTemperature(int temperature) {
        int oldTemp = this.temperature;
        this.temperature = temperature;
        logger.debug("Temperature set to {}. Old temperature was {}.", this.temperature, oldTemp);

        if (this.temperature > 50) {
            logger.warn("Temperature has risen above 50 degrees.");
        } else if (this.temperature > 30) {
            logger.info("Temperature has risen above 30 degrees.");
        }
    }
}