package com.shareskill.service;
import java.net.Inet4Address;
import java.util.List;

import com.shareskill.model.TLocalnetmemo;
	public interface LocalNetMemoService {
		
		/**浏览所有备忘便签*/
		public List<TLocalnetmemo> browseAllNetMemo();
		/**浏览指定备忘便签*/
		public TLocalnetmemo loadNetMemoById(int id);
		/**浏览指定备忘便签通过ip地址*/
		public TLocalnetmemo loadNetMemoById(Inet4Address ip);
		/**删除指定备忘便签*/
		public TLocalnetmemo delNetMemoById(int id);
		/**新增或修改指定备忘便签*/
		public Boolean saveOrUpdateNetMemo(TLocalnetmemo netMemo);
	}