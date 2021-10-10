package com.iotcloud.system.web.control;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iotcloud.common.core.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuControl {

    @GetMapping("/menu")
    public Result<JSONArray> getMenu(){
        JSONArray jsonArray = new JSONArray();
        JSONObject j1 = new JSONObject();
        j1.put("id","1");
        j1.put("authName","仪表盘");
        j1.put("path","statistics");
        j1.put("children","");

        JSONObject j2 = new JSONObject();
        j2.put("id","2");
        j2.put("authName","项目管理");
        j2.put("path","projects");
        j2.put("children","");

        JSONArray jsonArray3 = new JSONArray();

        JSONObject j31 = new JSONObject();
        j31.put("id","31");
        j31.put("authName","设备管理");
        j31.put("path","devices");
        j31.put("children","");

        JSONObject j32 = new JSONObject();
        j32.put("id","32");
        j32.put("authName","传感器管理");
        j32.put("path","sensors");
        j32.put("children","");


        JSONObject j33 = new JSONObject();
        j33.put("id","33");
        j33.put("authName","数据管理");
        j33.put("path","datas");
        j33.put("children","");
        jsonArray3.add(j31);
        jsonArray3.add(j32);
        jsonArray3.add(j33);

        JSONObject j3 = new JSONObject();
        j3.put("id","3");
        j3.put("authName","设备管理");
        j3.put("path","devices");
        j3.put("children",jsonArray3);

        JSONObject j41 = new JSONObject();
        j41.put("id","41");
        j41.put("authName","用户列表");
        j41.put("path","users");
        j41.put("children","");
        JSONArray jsonArray4 = new JSONArray();
        jsonArray4.add(j41);
        JSONObject j4 = new JSONObject();
        j4.put("id","4");
        j4.put("authName","用户管理");
        j4.put("path","users");
        j4.put("children",jsonArray4);


        jsonArray.add(j1);
        jsonArray.add(j2);
        jsonArray.add(j3);
        jsonArray.add(j4);
        return Result.ok(jsonArray,"请求成功");
    }
}
