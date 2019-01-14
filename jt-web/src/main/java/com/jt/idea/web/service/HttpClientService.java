package com.jt.idea.web.service;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Service
public class HttpClientService {

    @Autowired
    private RequestConfig requestConfig;
    @Autowired
    private HttpClient httpClient;

    public String doget(String uri, HashMap<String, String> params) {
        return doget(uri, params, null);
    }

    private String doget(String uri, HashMap<String, String> params, String charset) {
        if (StringUtils.isEmpty(charset)) {
            charset = "UTF-8";
        }
        String result = null;
        try {
            String url = null;
            if (params != null) {
                URIBuilder uriBuilder = new URIBuilder(uri);
                for (Map.Entry<String, String> stringStringEntry : params.entrySet()) {
                    uriBuilder.addParameter(stringStringEntry.getKey(), stringStringEntry.getValue());
                }
                url = uriBuilder.build().toString();
            }
            HttpGet httpGet = new HttpGet(url);
            httpGet.setConfig(requestConfig);
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(response.getEntity(), charset);
            }

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String doget(String uri) {
        return doget(uri, null, null);
    }
}
