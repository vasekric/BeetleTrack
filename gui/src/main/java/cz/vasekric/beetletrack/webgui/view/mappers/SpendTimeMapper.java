package cz.vasekric.beetletrack.webgui.view.mappers;

import cz.vasekric.beetletrack.domain.models.SpendTimeDO;
import cz.vasekric.beetletrack.domain.models.UserDO;
import cz.vasekric.beetletrack.webgui.view.models.SpendTime;
import cz.vasekric.beetletrack.webgui.view.models.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vasek on 08.12.2015.
 */
@Named
@ApplicationScoped
public class SpendTimeMapper {

    @Inject private UserMapper userMapper;

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
        final User user = userMapper.map(source.getUser());

        return SpendTime.builder()
                .time(source.getTime())
                .user(user)
                .build();
    }

    public SpendTimeDO map(SpendTime source) {
        final UserDO user = userMapper.map(source.getUser());

        return SpendTimeDO.builder()
                .time(source.getTime())
                .user(user)
                .build();
    }
}
