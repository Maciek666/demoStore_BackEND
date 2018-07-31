package pl.time4it.demo_store_2.mapper;

import pl.time4it.demo_store_2.dtos.OpinionDto;
import pl.time4it.demo_store_2.entities.Opinion;

public class OpinionMapper implements Mapper<Opinion,OpinionDto>{


    @Override
    public OpinionDto map(Opinion from) {
        return new OpinionDto(
                from.getContent(),
                from.getRating(),
                from.getProduct().getSerialNo()
                );
    }
}
