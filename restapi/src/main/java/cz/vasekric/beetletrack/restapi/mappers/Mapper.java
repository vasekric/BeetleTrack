package cz.vasekric.beetletrack.restapi.mappers;

import cz.vasekric.beetletrack.api.models.SpendTime;
import cz.vasekric.beetletrack.domain.models.SpendTimeDO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vasek on 11.12.2015.
 */
public interface Mapper <Source, Target>{

    Target map(Source source);

    default List<Target> map(List<Source> source) {
        if(source == null) {
            return null;
        }
        return source.stream().map(this::map).collect(Collectors.toList());
    }
}
