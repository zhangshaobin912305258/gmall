package com.zhang.gmall.search;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhang.gmall.entity.PmsSearchSkuInfo;
import com.zhang.gmall.entity.PmsSkuInfo;
import com.zhang.gmall.service.SkuService;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.search.builder.SearchSourceBuilder;
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
            Index put = new Index.Builder(pmsSearchSkuInfo)
                    .index("gmall")
                    .type("PmsSkuInfo")
                    .id(pmsSearchSkuInfo.getId())
                    .build();
            jestClient.execute(put);
        }
    }

    /**
     * 如何定义复杂查询
     * 查询所有名字中带有华为
     * 查询所有4寸以下，16G内存的手机
     * Query {
     * bool: { //先过滤，后查询
     * filter:{term,term}
     * must:{match}
     * }
     * }
     */
    public void search() throws IOException {
        //用Api执行复杂查询
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //query  from size highlight
        searchSourceBuilder.query("");
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(20);
        searchSourceBuilder.highlight(null);
        //bool
        //filter
        //must
        Search search = new Search.Builder("{\n" +
                "  \"query\": {\n" +
                "    \"bool\": {\n" +
                "      \"filter\": [\n" +
                "        {\n" +
                "          \"term\": {\n" +
                "            \"skuAttrValueList.valueId\": \"39\"\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"term\":{\n" +
                "            \"skuAttrValueList.valueId\": \"43\"\n" +
                "          }\n" +
                "        }\n" +
                "      ],\n" +
                "      \"must\": [\n" +
                "        {\n" +
                "          \"match\": {\n" +
                "            \"skuName\": \"华为\"\n" +
                "          }\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  }\n" +
                "}")
                .addIndex("gmall")
                .addType("PmsSkuInfo")
                .build();
        SearchResult searchResult = jestClient.execute(search);
        List<SearchResult.Hit<PmsSearchSkuInfo, Void>> hits = searchResult.getHits(PmsSearchSkuInfo.class);
        for (SearchResult.Hit<PmsSearchSkuInfo, Void> hit : hits) {
            PmsSearchSkuInfo skuInfo = hit.source;

        }
    }

}
