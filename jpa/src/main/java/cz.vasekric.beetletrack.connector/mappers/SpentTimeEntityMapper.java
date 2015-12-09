package cz.vasekric.beetletrack.connector.mappers;

import cz.vasekric.beetletrack.connector.models.SpendTime;
import cz.vasekric.beetletrack.connector.models.User;
import cz.vasekric.beetletrack.domain.models.SpendTimeDO;
import cz.vasekric.beetletrack.domain.models.UserDO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vasek on 06.12.2015.
 */
@Named
@ApplicationScoped
public class SpentTimeEntityMapper {

    @Inject private UserEntityMapper userMapper;

    public List<SpendTime> mapDO(List<SpendTimeDO> source) {
        if(source == null) {
            return new ArrayList<>();
        }
        return source.stream().map(this::map).collect(Collectors.toList());
    }

    public List<SpendTimeDO> map(List<SpendTime> source) {
        if(source == null) {
            return new ArrayList<>();
        }
        return source.stream().map(this::map).collect(Collectors.toList());
    }

    public SpendTime map(SpendTimeDO source) {
        if(source == null) {
            return null;
        }
        final User user = userMapper.map(source.getUser());
        return SpendTime.builder()
                .user(user)
                .time(source.getTime().toHours())
                .build();
    }

    public SpendTimeDO map(SpendTime source) {
        if(source == null) {
            return null;
        }
        final UserDO user = userMapper.map(source.getUser());
        return SpendTimeDO.builder()
                .user(user)
                .time(Duration.ofHours(source.getTime()))
                .build();
    }
}
