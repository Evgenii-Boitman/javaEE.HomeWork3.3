package by.boitman.database.repository;

import by.boitman.database.dto.CardFilter;
import by.boitman.database.entity.CardEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepositoryExtension {
    List<CardEntity> findByFilter(CardFilter filter);
}
