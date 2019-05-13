package com.zlpg.control;


import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.zlpg.service.ExcelWrite;
import com.zlpg.service.GetAccess;



public class Talkvotice {

	@SuppressWarnings({ "rawtypes", "unchecked", "static-access" })
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileInputStream in = null;
		try {
			ExcelWrite excel=new ExcelWrite();

			String fileDir="E:/project.xls";
			Properties properties = new Properties();
			in = new FileInputStream("zlpg.properties");
			properties.load(in);
			String Appkey = properties.getProperty("touken.Appkey");
			String Appsecret = properties.getProperty("touken.Appsecret");
			String processCode=properties.getProperty("processCode");
			System.out.println(processCode);
			GetAccess test=new GetAccess();
			String accessToken =  test.getSign(Appkey,Appsecret);
			System.out.println(accessToken);
			//批量获取PID
			List<String> pids=test.getPid(accessToken,processCode);
			List<Map> exceList=new ArrayList();
			//根据pid获取审批详情的list<map>
			for (String pid : pids) {
				Map<String, String> map=test.getPinstance(accessToken, pid);
				exceList.add(map);
			}
			//获取表头数组title

			Map<String, String> mapKet=exceList.get(0);
		    Set<String> keys=mapKet.keySet();
			String title[] = new String[mapKet.size()];
	        Iterator<String> iterator1=keys.iterator();
	       	int i=0;
	        while (iterator1.hasNext()){
	        	title[i]=iterator1.next();
	        	i++;     
	        }
	        for (int j = 0; j < title.length; j++) {
	        	System.out.println(title[j]);
			}
	        
	        //生成最新的excel
	        if (ExcelWrite.fileExist(fileDir)) {
	        	ExcelWrite.deleteExcel(fileDir);
	        	ExcelWrite.createExcel(fileDir,"sheet1",title);
			}else {
	        	ExcelWrite.createExcel(fileDir,"sheet1",title);
			}
			
			excel.writeToExcel(fileDir, "sheet1", exceList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
