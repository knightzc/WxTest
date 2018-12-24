package com.example.demo.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageUtil {

    public static String streamToString(HttpServletRequest request){
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
    /**
     * 将微信的请求中参数转成map
     * @param request
     * @return
     */
    public static Map<String,String> xmlToMap(HttpServletRequest request){
        Map<String,String> map = new HashMap<String,String>();
        SAXReader reader = new SAXReader();
        InputStream in = null;
        try {
            in = request.getInputStream();
            Document doc = reader.read(in);
            Element root = doc.getRootElement();
            List<Element> list = root.elements();
            for (Element element : list) {
                map.put(element.getName(), element.getText());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return map;
    }

    public static Map<String,String> xmlToMapWithDecrypt(HttpServletRequest request,String token,String encodingAesKey,String appId) {
        String fromXML = streamToString(request);
        WXBizMsgCrypt pc = null;
        String result = "";
        Map<String,String> map = new HashMap<String,String>();
        Document doc = null;
        try {
            pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
            result = pc.decryptMsg(request.getParameter("msg_signature"),request.getParameter("timestamp"),request.getParameter("nonce"),fromXML);
            doc = DocumentHelper.parseText(result);
        } catch (AesException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = doc.getRootElement();
        List<Element> list = root.elements();
        for (Element e : list) {
            map.put(e.getName(), e.getText());
        }
        return map;
    }

    public static String encryptMessage(HttpServletRequest request,String token,String encodingAesKey,String appId,String replyXml){
        WXBizMsgCrypt pc = null;
        String result = "";
        try {
            pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
            result = pc.encryptMsg(replyXml,request.getParameter("timestamp"),request.getParameter("nonce"));
        } catch (AesException e) {
            e.printStackTrace();
        }
        System.out.println("++++++++++++++" + result);
        return result;

    }
}
