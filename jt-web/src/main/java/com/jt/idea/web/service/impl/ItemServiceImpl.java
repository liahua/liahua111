package com.jt.idea.web.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.idea.common.po.TbItem;
import com.jt.idea.web.service.HttpClientService;
import com.jt.idea.web.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private HttpClientService httpClient;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public TbItem findItemById(Long itemId) {
        String uri="http://manage.jt.com/web/item/findItemById";
        HashMap<String, String> params = new HashMap<>();
        params.put("itemId", String.valueOf(itemId));
        String resultJson=httpClient.doget(uri,params);
        TbItem tbItem=null;
        try {
            tbItem= objectMapper.readValue(resultJson, TbItem.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tbItem;
    }
}
