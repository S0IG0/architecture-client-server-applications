package com.library.servicelibrary.service.base.impl;

import com.library.servicelibrary.entity.models.base.BaseEntity;
import com.library.servicelibrary.exception.DuplicateException;
import com.library.servicelibrary.exception.NotFoundItemException;
import com.library.servicelibrary.service.base.BaseCRUDService;
import com.library.servicelibrary.utils.CopyNames;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

public class BaseCRUDServiceImpl<T extends BaseEntity<ID>, ID extends Serializable> implements BaseCRUDService<T, ID> {

    protected final JpaRepository<T, ID> repository;

    public BaseCRUDServiceImpl(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public T findById(ID id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundItemException("Not found item"));
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T create(T item) {
        try {
            return repository.save(item);
        } catch (DataIntegrityViolationException exception) {
            
            throw new DuplicateException(exception.getMessage());
        }
    }

    @Transactional
    @Override
    public T update(ID id, @NotNull T item) {
        T existingItem = repository.findById(id).orElseThrow(() -> new NotFoundItemException("Not found item"));
        BeanUtils.copyProperties(existingItem, item, CopyNames.getNullPropertyNames(item));
        item.setUpdated(null);
        return repository.save(item);
    }

    @Override
    public void deleteById(ID id) {
        repository.delete(repository.findById(id).orElseThrow(() -> new NotFoundItemException("Not found item")));
    }
}
