package com.zhang.gmall.service;

import com.zhang.gmall.entity.PmsBaseCatalog1;
import com.zhang.gmall.entity.PmsBaseCatalog2;
import com.zhang.gmall.entity.PmsBaseCatalog3;

import java.util.List;

public interface CatalogService {
    List<PmsBaseCatalog1> getCatalog1();

    List<PmsBaseCatalog2> getCatalog2(Long catalog1Id);

    List<PmsBaseCatalog3> getCatalog3(Long catalog2Id);
}
