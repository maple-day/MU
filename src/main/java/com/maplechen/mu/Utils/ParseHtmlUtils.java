package com.maplechen.mu.Utils;

import com.maplechen.mu.Domain.Msg;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * Created by Administrator on 2017/5/6.
 */
public class ParseHtmlUtils {
    public static void parseHtml(String content , List<Msg> datalist){
        Document doc = Jsoup.parse(content);
        Elements select = doc.select("table.black").select("tr");
        for (Element element : select) {
            String text = element.select("td>a").text();
            String url = element.select("td>a").attr("href");
            String date = element.select("td>font").text();
            Msg msg = new Msg(text, date, url);
            datalist.add(msg);
        }
    }
}
