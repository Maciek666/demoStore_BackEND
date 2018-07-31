package pl.time4it.demo_store_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.time4it.demo_store_2.dtos.ProducerDto;
import pl.time4it.demo_store_2.entities.Producer;
import pl.time4it.demo_store_2.mapper.ProducerMapper;
import pl.time4it.demo_store_2.repositories.ProducerRepository;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/")
public class ProducerController {

    @Autowired
    ProducerRepository producerRepository;



    @GetMapping("producers")
    public List<ProducerDto> getProducers() {

        List<ProducerDto> producerDtos = new ArrayList<>();

            List<Producer> producers = producerRepository.findAll();
            ProducerMapper mapper = new ProducerMapper();
            for (Producer c : producers) {
                ProducerDto producerDto = mapper.map(c);
                producerDtos.add(producerDto);
        }

        return producerDtos;
    }
}
