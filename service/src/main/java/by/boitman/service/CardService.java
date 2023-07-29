package by.boitman.service;

import by.boitman.database.dto.CardFilter;
import by.boitman.database.entity.CardEntity;
import by.boitman.database.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

}
