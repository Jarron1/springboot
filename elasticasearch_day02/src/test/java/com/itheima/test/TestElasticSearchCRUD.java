package com.itheima.test;

import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.PreparedStatement;
import java.util.concurrent.ExecutionException;

public class TestElasticSearchCRUD {


    @Test
    public void fun01() throws UnknownHostException {

        Settings settings = Settings.EMPTY;

        TransportClient client = new PreBuiltTransportClient(settings).addTransportAddress
                (new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));


        client.admin().indices().prepareCreate("blog3").get();


        client.close();



    }


    @Test
    public void fun02() throws UnknownHostException {

        Settings empty = Settings.EMPTY;

        TransportClient client = new PreBuiltTransportClient(empty).addTransportAddress(
                new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300)
        );

        client.admin().indices().prepareDelete("blog3").get();


        client.close();
    }



    @Test
    public void fun03() throws IOException, ExecutionException, InterruptedException {

        Settings settings = Settings.EMPTY;

        TransportClient client = new PreBuiltTransportClient(settings).addTransportAddress(

                new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300)
        );
//创建名称为blog2的索引
        client.admin().indices().prepareCreate("blog3").get();


        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .startObject("article")
                .startObject("properties")
                .startObject("id")
                .field("type","long")
                .endObject()
                .startObject("title")
                .field("type","string")
                .field("store","true")
                .field("analyzer","ik_smart")
                .endObject()
                .startObject("content")
                .field("type","string")
                .field("store","true")
                .field("analyzer","ik_smart")
                .endObject()
                .endObject()
                .endObject()
                .endObject();
        // 创建映射
        PutMappingRequest mapping = Requests.putMappingRequest("blog2").type("article").source(builder);
        client.admin().indices().putMapping(mapping).get();
        //释放资源
        client.close();
    }


}
