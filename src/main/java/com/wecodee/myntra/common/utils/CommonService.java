package com.wecodee.myntra.common.utils;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import net.minidev.json.JSONObject;

@Service
public class CommonService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public <T> JSONObject getPaginationData(int pageNumber, int pageSize, List<T> list, Long count) {
		Page<T> pageData = new PageImpl<T>(list, PageRequest.of(pageNumber, pageSize), count);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("currentPage", pageData.getNumber());
		jsonObject.put("totalItem", pageData.getTotalElements());
		jsonObject.put("totalPages", pageData.getTotalPages());
		jsonObject.put("items", pageData.getContent());
		return jsonObject;
	}
}
