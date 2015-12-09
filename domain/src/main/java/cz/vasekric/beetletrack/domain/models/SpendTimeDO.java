package cz.vasekric.beetletrack.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.Duration;

/**
 * Created by vasek on 04.10.2015.
 */
@Data
@AllArgsConstructor
@Builder
public class SpendTimeDO implements Serializable {
    private UserDO user;
    private Duration time;
}
