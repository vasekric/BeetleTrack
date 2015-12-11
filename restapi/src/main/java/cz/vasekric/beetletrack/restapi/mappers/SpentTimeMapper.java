package cz.vasekric.beetletrack.restapi.mappers;

import cz.vasekric.beetletrack.api.models.SpendTime;
import cz.vasekric.beetletrack.domain.models.SpendTimeDO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vasek on 11.12.2015.
 */
@Component
public class SpentTimeMapper implements Mapper<SpendTimeDO, SpendTime> {

    @Override
    public SpendTime map(SpendTimeDO source) {
        if(source == null) {
            return null;
        }
        final SpendTime spendTime = new SpendTime();
        spendTime.time = source.getTime().toHours();

        return spendTime;
    }
}
