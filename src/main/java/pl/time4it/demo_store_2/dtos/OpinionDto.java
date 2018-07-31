package pl.time4it.demo_store_2.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OpinionDto {

    private String content;

    private int rating;

    private String serialNo;
}
