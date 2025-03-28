package com.unstage.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AService {
    private final ARepository aRepository;
    private final QueryDSLRepository queryDSLRepository;

    public AEntity create() {
        return aRepository.save(new AEntity());
    }

    public void fetch() {
        queryDSLRepository.test();
    }
}
