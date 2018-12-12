package com.kingold.educationblockchain.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


@Controller

public class StudentInfoAndCertsController {
    private static final String  authCode = "Basic aGVsaXgubGl1QG9yYWNsZS5jb206QWJjZDEyMzQ=";
    @RequestMapping(value="/certList",method = RequestMethod.GET)
    public String list(ModelMap model) {
        try {
            model.addAttribute("title", "学生信息及所获证书");

            String urlStr="http://yungoal-kingoldcloud.blockchain.ocp.oraclecloud.com:443/restproxy1/bcsgw/rest/v1/transaction/invocation";

            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setReadTimeout(30 * 1000);
            connection.setRequestProperty("Content-Type","application/json");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
            connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
            connection.setRequestProperty("Pragma", "no-cache");
            connection.setRequestProperty("Cookie", "s_fid=7BDFF4B7312E42FA-1CE1FA963D50F40E; atgRecVisitorId=13DCMNz0ImJZvdV-WscfT76Mmjqx--10q75zllk2_NbkWysD32B; s_cc=true; xdVisitorId=13DCMNz0ImJZvdV-WscfT76Mmjqx--10q75zllk2_NbkWysD32B; yungoalconsole_id=yungoalconsole-bcsc-1; X-Oracle-BMC-LBS-Route=f99fd83a64ff31b0c438a0cd04dc028f96b1a6a42f7998bbe576bec4a3a87233788a87c6bbf38dad; gpw_e24=https%3A%2F%2Fmyservices-kingoldcloud.console.oraclecloud.com%2Fmycloud%2Fcloudportal%2Fdashboard; s_nr=1544615602220-Repeat; s_sq=%5B%5BB%5D%5D; atgRecSessionId=evWiPlolVZeYY7PT4XLpv0eBritLQS2Glh1dePmy0JWjlKuj6KSU!159342199!2079983326; ORA_OCIS_CG_SESSION_idcs-a52ddeb6b13943bb9390c2b897d6b539_yungoal-kingoldcloud.blockchain.ocp.oraclecloud.com=BAkAAMoIAAAQIMoIAACwCAAAEADG9OLBdQCvMV8HRDwQS8CErlOO8fuFoYCHDuVcizFYtXdiiALIIo-i4PZfDPKEPrHYFCP1zk1RXuHw2uNrf4_5bUuhP3v-7kETFn31yw-XRSNHx0uisPUweMvCGQDNEF5JeV4sgglNXheo37QSa5_fLpB-2UMtbwYxF3SMZUTYPP8rOvQq_OJL80D4kdu7ZBMr8CC5BNziIPQl9TIs50RYWj_xXM5_sJ988dv8kMz-Qfg__0RWfjFKJTj4WwPpISUK4L7AjS_vmNafw1zi643jG-nfFp4tkWhWB-qG-7OXLMd7KcLObg8xJkRY1wb4vgc-_azF_36D0Tz9_Jy9QyOF7pU2xIeVJ8HZIFSwHfNuigI4dtc8FwOZJBrCzEnnmDPKNhlrNjENO9A_Ltq6heGF6PrWJUJRQoAEh8A8LqhPP9ze9w6piwqil_bThS7TkwopkH1r32nTe0RnhMGIHEyzevBHgHHAyDphI4vf9vShGZZHdMGMllLeIlfE0TscC2dQ2W3sxbwys227e4ddyY4Covrt4XAd0ch4QAUg7q_kYdROLuXPz_GBQ92m3ytQYpVXdH_wZeHaZKSjtG8j6XHqUA41GcaO61q-UeikqdZH6t1m39SrZrZe8yIwBv7Mb7JgW0oHSfevEaIcW2UnG8IpmcKi4qMnWv6sE62LQwV3TJx_w6SmjM-9sleNy8E5XUy9LzGahlXdPnoaqYdWDv_Htw-DXXGEfX8Iz5qPNiYWLMXjcMcsduTtuWwe-wUmBqYJYKax6putBMP8nXoh2rzcVEQdCmnd97XSflOwyQzptEy7qqFw8nNB6QOzYsqL5plteuwAq6HhXampXrp7kFwMj-UhEuP2zKZ35CbUIuG3iOdvMPHzoZoGp-OVGOlmzFAp5TgsLqkBqUhTANPJ1-jzUaYkjADM4_9XM3b0hv41cXoMKSKnmYCkvfEWa_nyu6-qKyxphph0tVn5ssdUKjW6FlNNakzUK79pIfCMLWMZ9QMgl34Ohq0F0NRmyxlWwgIGolYGQ8o_P3zFgyUBT-1AWGtkJigU937xyhAqLWzBulqsICfXPAXOxtIOkM-sMEUZ9ejXxdrsW9pkPRmndx-pwwPwsTXIH5i0RyQf7lWtd_BpSIdh9nARivhYYY8k4wKYRaB4eOXhbUkURpZrU4bQqPZUp50s0kV90O_IBKnqH_Z22kJdZlhahs_jFIRwGGAOWPq8dxU6VuuzQJrTcwylE819tWX2U1ZDOT_0lw2NpTWA4JNY9-uMsvzIeagTQAAES5si4PS1pPQR3LArt2RIyCsG15HeKbNthcz6LdGB31G0hyzSTRRXDdacswFHVPyAAycDbnw2Mro9RPJoUlOPXbjCSAEuNsG-8fKu0mkwoWUl6JBOSSqUmxEowy1wovEvyhwgBE-b9ecVrvQ0XOV-LWn_QDdeFM58jRotBLMpXRz3KggTehF5Hy39c4ouJ9VAcicUzQ8r4UDsYFlG3UuyuytPJaZf8GhoCSqg8vc_yDtiS-DUZKQAIv_n1-YYRJiUWX-sVi0IbAUdMl8YhK7-B3YBmNhkAiHUZNiS_t1cpqWmMjB6ktehlQquLqd0FVX8hXVuk_ALi5GJDZzBMwkFZRjAbeEx_NrYK4I6fGI7DoaZP0sAnCEOjqwOSWQRI3P5DJZf06goV6_uIR0X2Oa153_HLwFQKeWyxNSc4D3kdJzwiL6WGoDQlIa01iUIU4H7Elixs84xcwr3RQtHHbyZ7IX_-sxPCxBGwg-GbohVZUH85NTfHBMa1MXjDQY1UlHa2bfFRFvR0cW-ycEct81HG-EUelrT8Snq6GhkxTD6D7Wo27rsxe6dbME2I02BID55ClspsnpoYjIpUzlELcqOBBdobAj8aixmak37Q4OpNjf58PzNu-pFvXSyd1W8Fm5ZlXIsXGMt85gKPpMkuAznEmaC_lDBhz3TlTYbrreCX8H7TDVPe0lxfjKxqQh9fBUzISb7AdwZimVYSyvgP1daaZEb1Qw7XMW70r6sYwRWEMDZofKE8Dz6yYXdI31pbxrTMhrQUi3DvYvK-oLmpBiiMCy-JLtCFvTPI5gnSpOUZfTBCdV1IqW4su0d7Q3N6Eepw88C_E_G413IhK8E5oDeHFL-VVikux9DzKuaOyjvyoWUDJ2984OiGS6Y6UwUs-05TbshEUlVUp6tpZ3ROh9W0fUahebVV8uBRVnzSFtEMVCN1ghIzeFkO5Ty79Zkxx4FgchN_pdqHw4gWRcTfRGnfqUtbmMD4KGbyWAQphN4UAxf1waXUGNlyWh0EH58ZE8QTpAAIX3YexPl-5BhRHt3y34VKbtycuYHnRln8ypYmFBoZ277hilZ-Dh2ZEHN5F4hCQi0pfeZA34BUaYlwLsdLzNLCLxkKHsrNaXFGkGUHaunfr6RMM2uIS7SuA6GCQAdQy4WwShilTpYGtKWeZdAAXWYwQcn4CeA-eM47qzaGhsdAfriBKsJD1HvIwunkyaLgZqzqbRyxz7oEQ1nxbqp91k-4OncJxpWGCvbiqSUfJMEI_bG6d0hxTCPYMjDVq-5QFa34pvpai-QBtlVnwzndvBJVlzxDaZk9MRqVlegtF-bkK7H2OaC1i6nbtQDv9F8lW_5uyEYZ5rzAaFSUaKhgdiJYoF35szvDgdIhYRKkdpS9Q7v_Duxzy4SlbQXO7Z72i04lqX5mTJRiMMAw2QZa6f7rd9KcfItr4csp5pnm538LX3h-1w3eoJbvnuQOchFBbmX5RI3qVt0NvhftquoIJvoz57jyZroeeUtJGSKnPEkdhHNwYwKvsRaACEi7Bli8mdOk7aPlO0TdUvJlFPWQuO4JCDva1sXFa52w2_wyE9yON9aeIBEmsQSdmlxMjhhnl4ys1IArVNTbKaxoIElm2x_STWM2q_hDwhKwyZRQXyut0nktP1lMPJ_XNPE-c2DeSxrmuqd52s0diShjZOt-k2dMGl6Kpqz8EKDBWTsJlxHxeYRB4dLVRuQMMT5IdWf4ADV85R8xu4a44_ZGcEgh6bNDG5GB2S5fi0DE1A8Og");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.80 Safari/537.36");
            connection.setRequestProperty("Host", "yungoal-kingoldcloud.blockchain.ocp.oraclecloud.com");
            connection.setRequestProperty("Authorization","Basic aGVsaXgubGl1QG9yYWNsZS5jb206QWJjZDEyMzQ=");

            // 发送请求参数
            out.append("{\"channel\":\"channel1\",\"chaincode\":\"kingold\",\"method\":\"queryEventByEducationNo\",\"args\":[\"edu1\"],\"chaincodeVer\":\"v7\"");
            MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
            map.add("shopid","1");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
            return restTemplate.postForEntity(url, request,String.class);

            return response.getBody();

            model.addAttribute("json", result);
        }
        catch (Exception ex)
        {
            return "error";
        }

        return "studentinfoandcerts";
    }
}
