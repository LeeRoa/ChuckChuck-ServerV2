package cc.test.roa.service;

import cc.test.roa.dto.RoaDto;
import cc.test.roa.entity.Roa;
import cc.test.roa.repository.RoaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoaServiceImpl implements RoaService {

    private final RoaRepository roaRepository;

    @Transactional
    public void save(RoaDto dto) {
        roaRepository.save(Roa.of(dto.getName(), dto.getAge()));
    }


}
