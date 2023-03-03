package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Repository;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
@Repository
@Mapper
public class esOperation {
    public  String GetImageStr(String imgPath) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        String imgFile = imgPath;// 待处理的图片
        InputStream in = null;
        byte[] data = null;
        String encode = null; // 返回Base64编码过的字节数组字符串
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        try {
            // 读取图片字节数组
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            encode = encoder.encode(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return encode;
    }

    public  boolean GenerateImage(String imgData, String imgFilePath) throws IOException { // 对字节数组字符串进行Base64解码并生成图片
        if (imgData == null) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        OutputStream out = null;
        try {
            out = new FileOutputStream(imgFilePath);
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgData);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            out.write(b);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
            return true;
        }
    }
    public void Insert(String article_id,String text) throws IOException {
//        String a=text.substring(text.indexOf("data:image/png;base64,")+22,text.indexOf("\" alt=\"\""));
//        String b=text.substring(text.indexOf("data:image/png;base64,"),text.indexOf("\" alt=\"\""));
//        System.out.println(b);
//        System.out.println(a);
//        String path="D:/Desktop/大三上/实训/项目2.0/xbr/前端c/images"+article_id+".png";//前端文件路径
//        GenerateImage(a,path);//将base64码转化为图片并保存
//        String newHtml=text.replace(b,article_id+".png");//改变文章内容
//        System.out.println(newHtml);
        RestHighLevelClient ecClient =new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http"))
        );
        BulkRequest request =new BulkRequest();
        request.add( new IndexRequest().index("knowledge_library").source(XContentType.JSON,"article_id",article_id,"text",text).id(article_id));
        BulkResponse responses = ecClient.bulk(request,RequestOptions.DEFAULT);
        System.out.println(responses.getTook());
        System.out.println(responses.getItems());
        ecClient.close();;
    }
    List QueryText(String keyword) throws IOException {
        RestHighLevelClient ecClient =new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http"))
        );
        SearchRequest request=new SearchRequest();
        request.indices("knowledge_library");
        request.source(new SearchSourceBuilder().query(QueryBuilders.matchQuery("text",keyword )));
        SearchResponse response =ecClient.search(request,RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());
        List<String> result =new ArrayList<>();
        for(SearchHit hit : hits){//查出几条进行循环
            System.out.println(hit.getSourceAsString());
            result.add(hit.getSourceAsString());
        }
        return result;
    }
    public List QueryKeyword(String keyword)throws IOException{
        RestHighLevelClient ecClient =new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http"))
        );
        SearchRequest request=new SearchRequest();
        request.indices("knowledge_library");
        request.source(new SearchSourceBuilder().query(QueryBuilders.matchQuery("article_id",keyword )));
        SearchResponse response =ecClient.search(request,RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());
        List<String> result =new ArrayList<>();
        for(SearchHit hit : hits){//查出几条进行循环
            System.out.println(hit.getSourceAsString());
            result.add(hit.getSourceAsString());
        }
        return result;
    }
    public Integer updateArticle(String id,String content)throws IOException{
        System.out.println("5465465465465465465465465465");
        System.out.println(id);
        System.out.println(content);
        RestHighLevelClient ecClient =new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http"))
        );
        //修改数据
        UpdateRequest request= new UpdateRequest();
        request.index("knowledge_library").id(id);
        request.doc(XContentType.JSON,"text",content);
        UpdateResponse response = ecClient.update(request, RequestOptions.DEFAULT);
        System.out.println(response.getResult());
        ecClient.close();;
        return 1;
    }
}
