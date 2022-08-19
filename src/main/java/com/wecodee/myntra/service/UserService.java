package com.wecodee.myntra.service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wecodee.myntra.admin.dto.SearchFilterDTO;
import com.wecodee.myntra.constant.ResponseMessage;
import com.wecodee.myntra.model.User;
import com.wecodee.myntra.repository.UserRepository;
import com.wecodee.myntra.responseDTO.ApiResponse;
import net.minidev.json.JSONObject;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EntityManager entityManager;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	// create
	public ApiResponse<User> create(User user) {
		log.info("*** Inside user create method ***");
		try {
			if (user != null) {
				Integer recordVersion = 1;
				user.setRecordVersion(recordVersion);
				this.userRepository.save(user);
			}
			return ApiResponse.success(ResponseMessage.OPERATION_SUCCESS.getMessage(), user);
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage(), user);
		}
	}

	// get
	public ApiResponse<User> get(String userId) {
		log.info("**** Inside user get method ***");
		try {
			User user = new User();
			if (userId != null) {
				user = userRepository.getByUserId(userId);
			}
			return ApiResponse.success(ResponseMessage.OPERATION_SUCCESS.getMessage(), user);
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

	// get All
	public ApiResponse<List<User>> getAllUsers() {
		log.info("*** Inside get all method ***");
		try {
			List<User> user = new ArrayList<User>();
			user = userRepository.findAll();
			return ApiResponse.success(ResponseMessage.OPERATION_SUCCESS.getMessage(), user);
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

	// delete
	public ApiResponse<User> deleteByUserId(String userId) {
		log.info("*** Inside user delete method ***");
		try {
			if (userId != null) {
				this.userRepository.deleteById(userId);
			}
			return ApiResponse.success(ResponseMessage.OPERATION_SUCCESS.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

//	update
	public ApiResponse<User> update(User user) {
		log.info("*** Inside user update method ***");
		try {
			User userDb = userRepository.getByUserId(user.getUserId());
			Integer recordVersion = userDb.getRecordVersion() + 1;
			user.setRecordVersion(recordVersion);
			userRepository.save(user);
			return ApiResponse.success(ResponseMessage.OPERATION_SUCCESS.getMessage(), user);
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

	public ApiResponse<JSONObject> getApprovedRecords(SearchFilterDTO<User> filterDto) {
		log.info("*** Inside user getApprovedRecords method ***");
		try {
			log.info("approve filter");
			JSONObject jSONPObject = new JSONObject();
			User user = filterDto.getFilterFieldValues();

			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<User> criteria = builder.createQuery(User.class);
			Root<User> chk = criteria.from(User.class);
			List<Predicate> predicates = new ArrayList<Predicate>();

			log.info("Message :" + filterDto.getPageNumber());
			log.info("Size :" + filterDto.getPageSize());

			Pageable pageable = PageRequest.of(filterDto.getPageNumber(), filterDto.getPageSize(),
					Sort.by("userId").ascending());

			if (user.getUserId() != null) {
				predicates.add(
						builder.like(builder.lower(chk.get("userId")), "%" + user.getUserId().toLowerCase() + "%"));
			}

			if (user.getFirstName() != null) {
				predicates.add(builder.like(builder.lower(chk.get("firstName")),
						"%" + user.getFirstName().toLowerCase() + "%"));
			}

			if (user.getLastName() != null) {
				predicates.add(
						builder.like(builder.lower(chk.get("lastName")), "%" + user.getLastName().toLowerCase() + "%"));
			}

			predicates.add(builder.equal(chk.get("countryCode"), "+91"));
			criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

			// This query fetches the records as per the Page Limit
			List<User> result = entityManager.createQuery(criteria).setFirstResult((int) pageable.getOffset())
					.setMaxResults(pageable.getPageSize()).getResultList();

			// Create count query
			CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
			Root<User> userCount = countQuery.from(User.class);
			countQuery.select(builder.count(userCount))
					.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

			// Fetches the count of all records as per given criteria
			Long count = entityManager.createQuery(countQuery).getSingleResult();
			log.info("count->" + count);
			log.info("size->" + result.size());
			Page<User> pageData = new PageImpl<>(result, pageable, count);

			jSONPObject.put("pageNumber", pageData.getNumber());
			jSONPObject.put("totalItems", pageData.getTotalElements());
			jSONPObject.put("totalPages", pageData.getTotalPages());
			jSONPObject.put("PageContent", pageData.getContent());
			log.info("" + pageData.getContent().size());

			if (pageData.getContent().size() == 0)
				return ApiResponse.success(ResponseMessage.OPERATION_SUCCESS.getMessage(), jSONPObject);
			else
				return ApiResponse.success(ResponseMessage.OPERATION_SUCCESS.getMessage(), jSONPObject);
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

}
