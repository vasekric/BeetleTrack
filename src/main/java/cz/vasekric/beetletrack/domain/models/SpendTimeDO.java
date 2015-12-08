package cz.vasekric.beetletrack.domain.models;

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
public class SpendTimeDO {
    private UserDO user;
    private Duration time;
}
