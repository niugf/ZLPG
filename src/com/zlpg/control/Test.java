package com.zlpg.control;

import java.awt.print.Printable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.taobao.api.ApiException;

public class Test {
	public static void main(String[] args) throws IOException {
		FileInputStream in = null;
		Properties properties = new Properties();
		Map<String, String> map=new HashMap<String, String>();
		map.put("审批编号", "1231415");
		map.put("所在部门", "1231415");
		map.put("项目名称", "1231415");
		map.put("客户地址", "1231415");
		map.put("客户名称", "1231415");
		map.put("联系人以及电话", "1231415");

		Set<String> keys=map.keySet();
		Iterator<String> iterator1=keys.iterator();
		String title[] = new String[20];
		int i=0;
		while (iterator1.hasNext()){
			title[i]=iterator1.next();
			i++;
			
		}
		

			


	}
}
