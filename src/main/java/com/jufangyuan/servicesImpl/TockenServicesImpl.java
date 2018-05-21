package com.jufangyuan.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jufangyuan.beans.TockenBean;
import com.jufangyuan.daos.TockenDao;
import com.jufangyuan.services.TockenServices;

@Service("TockenServices")
public class TockenServicesImpl implements TockenServices{
	@Autowired
	TockenDao tockenDao;
	@Override
	public int setTocken(TockenBean bean) {
		// TODO Auto-generated method stub
		int flag = tockenDao.setTocken(bean);
		return flag;
	}

	@Override
	public TockenBean getTocken() {
		// TODO Auto-generated method stub
		TockenBean bean = tockenDao.getTocken();
		return bean;
	}

}
