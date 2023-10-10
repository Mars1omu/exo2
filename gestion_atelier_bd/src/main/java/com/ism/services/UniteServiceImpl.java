package com.ism.services;

import java.util.ArrayList;

import com.ism.entities.Unite;
import com.ism.repository.ITables;

public class UniteServiceImpl implements UniteService {

    private ITables<Unite> uniteRepository;

    public UniteServiceImpl(ITables<Unite> uniteRepository) {
        this.uniteRepository = uniteRepository;
    }

    public void setUniteRepository(ITables<Unite> uniteRepository) {
        this.uniteRepository = uniteRepository;
    }

    @Override
    public int add(Unite unite) {
        return uniteRepository.insert(unite);
    }

    @Override
    public ArrayList<Unite> getAll() {
        return uniteRepository.findAll();
    }

    @Override
    public int update(Unite unite) {
        return uniteRepository.update(unite);
    }

    @Override
    public Unite show(int id) {
        return uniteRepository.findById(id);
    }

    @Override
    public int remove(int id) {
        return uniteRepository.delete(id);
    }

    @Override
    public int[] remove(int[] ids) {
        int[] idsNotDeleted = new int[ids.length];
        int nbre = 0;
        for (int id : ids) {
            if (uniteRepository.delete(id) == 0) {
                idsNotDeleted[nbre++] = id;
            }
        }
        return idsNotDeleted;
    }
}
