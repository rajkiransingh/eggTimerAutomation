package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static utils.CommonConstants.EXPECTED_TIME;

public class ElapsedTimeFinder {

    private static final Logger logger = LogManager.getLogger(ElapsedTimeFinder.class);
    Long elapsedTime;

    public Integer getLoadDelay(Long currentTime, Long latestTime) {
        elapsedTime = latestTime - currentTime;
        if(elapsedTime>850) {
            long timeInSec = elapsedTime/1000;
            EXPECTED_TIME = Math.toIntExact(EXPECTED_TIME - timeInSec);
        }
        return EXPECTED_TIME;
    }
}
