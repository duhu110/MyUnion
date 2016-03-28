package com.qhduhu.myunion.service;

import java.util.List;

import com.qhduhu.myunion.entity.JfEntity;

public interface JfService {
	public boolean updateJf(JfEntity entity) throws Exception;
	public List<JfEntity> getJfs(int lastid) throws Exception;
}
