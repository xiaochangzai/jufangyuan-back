package com.jufangyuan.weixinpay;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

public class HttpRequest {
	//杩炴帴瓒呮椂鏃堕棿锛岄粯璁�10绉�
    private static final int socketTimeout = 10000;

    //浼犺緭瓒呮椂鏃堕棿锛岄粯璁�30绉�
    private static final int connectTimeout = 30000;
	/**
	 * post璇锋眰
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyStoreException 
	 * @throws KeyManagementException 
	 * @throws UnrecoverableKeyException 
	 */
	public static String sendPost(String url, Object xmlObj) throws ClientProtocolException, IOException, UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException {
		

        
		HttpPost httpPost = new HttpPost(url);
		//瑙ｅ喅XStream瀵瑰嚭鐜板弻涓嬪垝绾跨殑bug
        XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
        xStreamForRequestPostData.alias("xml", xmlObj.getClass());
        //灏嗚鎻愪氦缁橝PI鐨勬暟鎹璞¤浆鎹㈡垚XML鏍煎紡鏁版嵁Post缁橝PI
        String postDataXML = xStreamForRequestPostData.toXML(xmlObj);

        //寰楁寚鏄庝娇鐢║TF-8缂栫爜锛屽惁鍒欏埌API鏈嶅姟鍣╔ML鐨勪腑鏂囦笉鑳借鎴愬姛璇嗗埆
        StringEntity postEntity = new StringEntity(postDataXML, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(postEntity);

        //璁剧疆璇锋眰鍣ㄧ殑閰嶇疆
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
        httpPost.setConfig(requestConfig);
        
        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "UTF-8");
        return result;
	}
	/**
	 * 鑷畾涔夎瘉涔︾鐞嗗櫒锛屼俊浠绘墍鏈夎瘉涔�
	 * @author pc
	 *
	 */
	public static class MyX509TrustManager implements X509TrustManager {
		@Override
		public void checkClientTrusted(
				java.security.cert.X509Certificate[] arg0, String arg1)
				throws java.security.cert.CertificateException {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void checkServerTrusted(
				java.security.cert.X509Certificate[] arg0, String arg1)
				throws java.security.cert.CertificateException {
			// TODO Auto-generated method stub
			
		}
		@Override
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			// TODO Auto-generated method stub
			return null;
		}
      }
}
