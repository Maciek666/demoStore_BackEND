package pl.time4it.demo_store_2.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.time4it.demo_store_2.dtos.OpinionDto;
import pl.time4it.demo_store_2.entities.Opinion;
import pl.time4it.demo_store_2.entities.Product;
import pl.time4it.demo_store_2.mapper.OpinionMapper;
import pl.time4it.demo_store_2.repositories.OpinionRespository;
import pl.time4it.demo_store_2.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/")
public class OpinionController {

    @Autowired
    OpinionRespository opinionRespository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("opinion")
    public List<OpinionDto> getOpinions(@RequestParam(value = "serialNo") String serialNo) {

        List<OpinionDto> opinionDtos = new ArrayList<>();


        // opinionRespository.findOpinionsByProduct(serialNo);


        if (serialNo == null) {

            List<Opinion> opinions = opinionRespository.findAll();
            OpinionMapper mapper = new OpinionMapper();
            for (Opinion o : opinions) {
                OpinionDto opinionDto = mapper.map(o);
                opinionDtos.add(opinionDto);
            }

        } else {

            List<Opinion> opinions = opinionRespository.findOpinionsByProduct(serialNo);
            OpinionMapper mapper = new OpinionMapper();
            for (Opinion o : opinions) {
                OpinionDto opinionDto = mapper.map(o);
                opinionDtos.add(opinionDto);
            }

        }


        return opinionDtos;
    }

    @RequestMapping(value = "opinion/add", method = RequestMethod.POST)
    public ResponseEntity<Opinion> addOpinion(

            @RequestParam(value = "content") String content,
            @RequestParam(value = "reating") int reating,
            @RequestParam(value = "serialNo") String serialNo
            ) {


        Optional<Product> productOptional = productRepository.findBySerialNo(serialNo);

        if (productOptional.isPresent()) {

            Opinion opinion = new Opinion();

            opinion.setContent(content);
            opinion.setRating(reating);
            opinion.setSerialNo(serialNo);
            opinion.setProduct(productOptional.get());
            opinionRespository.save(opinion);

            return ResponseEntity.ok().build();

        } else {
            return ResponseEntity.badRequest().build();
        }

    }

}
