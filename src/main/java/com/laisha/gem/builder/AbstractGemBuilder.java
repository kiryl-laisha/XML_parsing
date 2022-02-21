package com.laisha.gem.builder;

import com.laisha.gem.entity.AbstractGem;
import com.laisha.gem.exception.ProjectException;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractGemBuilder {

    protected Set<AbstractGem> gems;

    protected AbstractGemBuilder() {
        gems = new HashSet<>();
    }

    protected AbstractGemBuilder(Set<AbstractGem> gems) {
        this.gems = gems;
    }

    public Set<AbstractGem> getGems() {
        return gems;
    }

    protected abstract void buildGems(String filename)  throws ProjectException;
}
