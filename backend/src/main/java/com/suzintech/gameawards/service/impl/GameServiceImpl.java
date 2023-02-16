package com.suzintech.gameawards.service.impl;

import com.suzintech.gameawards.domain.Game;
import com.suzintech.gameawards.domain.GameRepository;
import com.suzintech.gameawards.service.GameService;
import com.suzintech.gameawards.service.exception.BusinessException;
import com.suzintech.gameawards.service.exception.NoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository repository;

    @Override
    public List<Game> findAll() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "votes"));
    }

    @Override
    public Game findById(Long id) {
        return repository.findById(id).orElseThrow(NoContentException::new);
    }

    @Override
    public void insert(Game game) {
        if (Objects.nonNull(game.getId())) {
            throw new BusinessException("O ID é diferente de NULL na inclusão.");
        }

        repository.save(game);
    }

    @Override
    public void update(Long id, Game game) {
        var entity = findById(id);

        if (entity.getId().equals(game.getId())) {
            repository.save(game);
        } else {
            throw new BusinessException("Os ID's para alteração são divergentes.");
        }
    }

    @Override
    public void delete(Long id) {
        var entity = findById(id);

        repository.delete(entity);
    }

    @Override
    public void vote(Long id) {
        var entity = findById(id);
        entity.setVotes(entity.getVotes() + 1);

        update(id, entity);
    }
}
