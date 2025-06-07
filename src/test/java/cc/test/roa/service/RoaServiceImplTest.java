package cc.test.roa.service;

import cc.test.roa.dto.RoaDto;
import cc.test.roa.entity.Roa;
import cc.test.roa.repository.RoaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoaServiceImplTest {

    @Autowired
    private RoaService roaService;

    @Autowired
    private RoaRepository roaRepository;

    @Test
    @DisplayName("save")
    void  save() throws Exception {
        // given
        RoaDto dto = new RoaDto("dasda", 47);
        // when
        roaService.save(dto);

        // then
        Roa roa = roaRepository.findById(2L).orElse(null);
        Assertions.assertNotNull(roa);

    }
}
