package com.zlpg.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiBlackboardListtoptenRequest;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiProcessinstanceGetRequest;
import com.dingtalk.api.request.OapiProcessinstanceListidsRequest;
import com.dingtalk.api.response.OapiBlackboardListtoptenResponse;
import com.dingtalk.api.response.OapiBlackboardListtoptenResponse.OapiBlackboardVo;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiProcessinstanceGetResponse;
import com.dingtalk.api.response.OapiProcessinstanceGetResponse.FormComponentValueVo;
import com.dingtalk.api.response.OapiProcessinstanceGetResponse.ProcessInstanceTopVo;
import com.dingtalk.api.response.OapiProcessinstanceListidsResponse;
import com.dingtalk.api.response.OapiProcessinstanceListidsResponse.PageResult;
import com.taobao.api.ApiException;


public class GetAccess<E> {
	public String getSign(String Appkey,String Appsecret) {
		try {
			DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
			OapiGettokenRequest request = new OapiGettokenRequest();
//			request.setAppkey("dingqgxlug3k33o9a6na");
//			request.setAppsecret("xPBwIT5bQ3pcMyDDXNt0Vm3kCIMeGfHZlRBV5la29APBJesaRM_cuoMy7e6LmbP4");
			request.setAppkey(Appkey);
			request.setAppsecret(Appsecret);
			OapiGettokenResponse response = client.execute(request);
			String errmsg=response.getErrmsg();
			String token=response.getAccessToken();
			if ("ok"==errmsg) {
				return token;
			}else {
				return token;
			}

		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String msg="获取token失败";
			return msg;
		}

	}
	public List votice(String accessToken) {
		List<String> pidList=new ArrayList<String>();
		try {

		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/blackboard/listtopten");
		OapiBlackboardListtoptenRequest request = new OapiBlackboardListtoptenRequest();
		request.setUserid("manager9441");
		OapiBlackboardListtoptenResponse response;
		response = client.execute(request, accessToken);
		List<OapiBlackboardVo> result=response.getBlackboardList();
		for (OapiBlackboardVo bOapiBlackboardVo : result) {
			bOapiBlackboardVo.getTitle();
			bOapiBlackboardVo.getUrl();
		}
//		System.out.println("jsonobject="+jsonobject);
//		JSONObject Json = JSONObject.fromObject(jsonobject);
		
		return result;
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pidList.add("系统出现错误，请联系管理员解决");
			return pidList;
		}

	}
	public List<String> getPid(String accessToken,String processCode) {
		List<String> pidList=new ArrayList<String>();
		try {
		
		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/processinstance/listids");
		OapiProcessinstanceListidsRequest req = new OapiProcessinstanceListidsRequest();
//		req.setProcessCode("PROC-FFYJ5P8V-TIJZYZECMSKU32A46NH33-I97S2BNJ-2");
		req.setProcessCode(processCode);
		req.setStartTime(781804800L);
//		req.setEndTime(1539658184L);
//		req.setSize(10L);
//		req.setCursor(0L);
//		req.setUseridList("manager9441,manager9441");
		OapiProcessinstanceListidsResponse response = client.execute(req,accessToken);
		System.out.println(response.getBody());
		PageResult pResult=response.getResult();
		System.out.println(pResult);
		pidList=pResult.getList();
		
		return pidList;
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pidList.add("系统出现错误，请联系管理员解决");
			return pidList;
		}

		
	}
	public Map<String, String> getPinstance(String accessToken,String id) {
		Map<String, String> map = new HashMap<String, String>();
		try {
		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/processinstance/get");
		OapiProcessinstanceGetRequest request = new OapiProcessinstanceGetRequest();
		request.setProcessInstanceId(id);
		OapiProcessinstanceGetResponse response = client.execute(request,accessToken);
		System.out.println(response.getBody());
		ProcessInstanceTopVo pinstance = response.getProcessInstance();
		List<FormComponentValueVo> from=pinstance.getFormComponentValues();
		for (FormComponentValueVo fcv : from) {
			map.put(fcv.getName(), fcv.getValue());
		}
		return map;
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("msg", "系统出现错误，请联系管理员解决");
			return map;
		}
		
	}

}
