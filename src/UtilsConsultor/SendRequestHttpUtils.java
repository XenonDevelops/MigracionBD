/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UtilsConsultor;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Omr
 */
public class SendRequestHttpUtils {

    public String doPost(String[] persona) throws IOException {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://losimpuestos.com.mx/rfc/calcular-rfc.php");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("paterno", persona[0]));
        params.add(new BasicNameValuePair("materno", persona[1]));
        params.add(new BasicNameValuePair("nombre", persona[2]));
        params.add(new BasicNameValuePair("dia", persona[3]));
        params.add(new BasicNameValuePair("sexo", persona[4]));
        params.add(new BasicNameValuePair("entidad", persona[5]));
        params.add(new BasicNameValuePair("mes", persona[6]));
        params.add(new BasicNameValuePair("anno", persona[7]));
        httpPost.setEntity(new UrlEncodedFormEntity(params));
        HttpResponse response = httpclient.execute(httpPost);
        String HTML = EntityUtils.toString(response.getEntity());
//        System.out.println(HTML);
//        System.out.println("\n\n\n\n\n\n\n\n");
//        System.out.println(HTML.contains("<strong>CURP</strong>"));
//        System.out.println("\n\n\n\n\n\n\n\n");
        String[] array = HTML.split("<strong>CURP</strong>");
        String cut = array[1].substring(18);
        String curp = "";
        for (int i = 0; true; i++) {
            if (cut.charAt(i) == '<') {
                break;
            } else {
                curp += cut.charAt(i);
            }
        }
        int terminaCurp = 18 + curp.length() + 26;
        String dig = array[1].substring(terminaCurp, 62);
//    System.out.println(persona[0]+"-"+persona[1]+"-" +persona[2]);
    System.out.println(persona[0]+"-"+persona[1]+"-" +persona[2]+"-" +persona[3]+"-" +persona[4]);
    System.out.println("entidad"+persona[5]+"-"+persona[6]+"-" +persona[7]);
        System.out.println(curp+dig);
        
        return curp + dig;
    }

}