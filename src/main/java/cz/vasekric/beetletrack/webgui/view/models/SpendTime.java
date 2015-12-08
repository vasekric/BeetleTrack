package cz.vasekric.beetletrack.webgui.view.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Duration;
import java.time.Period;

/**
 * Created by vasek on 04.10.2015.
 */
@Data
@AllArgsConstructor
@Builder
public class SpendTime {
    private final User user;
    private final Duration time;
}
