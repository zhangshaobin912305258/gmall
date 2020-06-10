package com.zhang.gmall.search;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhang.gmall.entity.PmsSearchSkuInfo;
import com.zhang.gmall.entity.PmsSkuInfo;
import com.zhang.gmall.service.SkuService;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GmallSearchServiceApplicationTest {
    @Reference
    SkuService skuService;

    @Autowired
    JestClient jestClient;

    @Test
    public void testElasticSearch() throws IOException {
        List<PmsSkuInfo> pmsSkuInfos = skuService.getAllSku();
        List<PmsSearchSkuInfo> searchSkuInfos = pmsSkuInfos.stream()
                .map(pmsSkuInfo -> {
                    PmsSearchSkuInfo pmsSearchSkuInfo = new PmsSearchSkuInfo();
                    BeanUtils.copyProperties(pmsSkuInfo, pmsSearchSkuInfo);
                    pmsSearchSkuInfo.setId(String.valueOf(pmsSkuInfo.getId()));
                    pmsSearchSkuInfo.setProductId(String.valueOf(pmsSkuInfo.getProductId()));
                    pmsSearchSkuInfo.setCatalog3Id(String.valueOf(pmsSkuInfo.getCatalog3Id()));
                    pmsSearchSkuInfo.setSkuAttrValueList(pmsSkuInfo.getAttrValues());
                    return pmsSearchSkuInfo;
                }).collect(Collectors.toList());
        //导入es
        //执行elasticSearch的命令
        for (PmsSearchSkuInfo pmsSearchSkuInfo : searchSkuInfos) {
            Index put = new Index.Builder(pmsSearchSkuInfo).index("gmall").type("PmsSkuInfo").id(pmsSearchSkuInfo.getId()).build();
            jestClient.execute(put);
        }
    }
}
