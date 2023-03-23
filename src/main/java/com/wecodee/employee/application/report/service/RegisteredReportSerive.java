package com.wecodee.employee.application.report.service;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.wecodee.employee.application.admin.dto.SearchFilterDTO;
import com.wecodee.employee.application.common.utils.CommonService;
import com.wecodee.employee.application.common.utils.Helper;
import com.wecodee.employee.application.constant.ResponseMessage;
import com.wecodee.employee.application.model.Register;
import com.wecodee.employee.application.report.dto.ReportDTO;
import com.wecodee.employee.application.responseDTO.ApiResponse;

import net.minidev.json.JSONObject;

@Service
public class RegisteredReportSerive {

	final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private ExcelGenerateService excelGenerateService;

	@Autowired
	private CommonService commonService;

	public ApiResponse<JSONObject> searchRegisterRecords(SearchFilterDTO<ReportDTO> registerFilterDTO) {
		log.info("Inside searchAccount method");
		try {

			// Adding predicates
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Register> criteriaQuery = criteriaBuilder.createQuery(Register.class);
			Root<Register> applicatioRoot = criteriaQuery.from(Register.class);
			List<Predicate> predicates = new ArrayList<Predicate>();

			Pageable pageable = PageRequest.of(registerFilterDTO.getPageNumber(), registerFilterDTO.getPageSize(),
					Sort.by("id").ascending());

			if (registerFilterDTO.getFilterFieldValues().getFromDate() != null
					&& registerFilterDTO.getFilterFieldValues().getToDate() != null) {
				Date formattedFromDate = Helper.formattingDate(registerFilterDTO.getFilterFieldValues().getFromDate());
				Date formattedToDate = Helper.formattingDate(registerFilterDTO.getFilterFieldValues().getToDate());
				predicates.add(
						criteriaBuilder.between(applicatioRoot.get("dateOfBirth"), formattedFromDate, formattedToDate));
			}

			criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
			List<Register> result = entityManager.createQuery(criteriaQuery).setFirstResult((int) pageable.getOffset())
					.setMaxResults(pageable.getPageSize()).getResultList();
			System.out.println("registered record result:" + result);

			CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
			Root<Register> registerCount = countQuery.from(Register.class);
			countQuery.select(criteriaBuilder.count(registerCount))
					.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));

			// Fetches the count of all records as per given criteria
			Long count = entityManager.createQuery(countQuery).getSingleResult();
			log.info("count->" + count);
			log.info("size->" + result.size());
			Page<Register> pageData = new PageImpl<Register>(result, pageable, count);
			if (pageData.getContent().size() == 0)
				return ApiResponse.success(ResponseMessage.NO_RECORDS_FOUND.getMessage(),
						commonService.getPaginationData(registerFilterDTO.getPageNumber(),
								registerFilterDTO.getPageSize(), result, count));
			else
				return ApiResponse.success(ResponseMessage.OPERATION_SUCCESS.getMessage(),
						commonService.getPaginationData(registerFilterDTO.getPageNumber(),
								registerFilterDTO.getPageSize(), result, count));
		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

	public ByteArrayInputStream registeredAccountsExcel(ReportDTO registerFilterDTO) throws FileNotFoundException {
		ByteArrayInputStream byteArrayInputStream = null;
		List<Register> registeredList = getRegisteredAccountList(registerFilterDTO);
		if (registeredList != null && registeredList.size() > 0) {
			byteArrayInputStream = excelGenerateService.registeredDataExcel(registeredList, registerFilterDTO);
		}
		return byteArrayInputStream;
	}

	private List<Register> getRegisteredAccountList(ReportDTO registerFilterDTO) {
		log.info("Inside searchAccount method");

		// Adding predicates
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Register> criteriaQuery = criteriaBuilder.createQuery(Register.class);
		Root<Register> applicatioRoot = criteriaQuery.from(Register.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		if (registerFilterDTO.getFromDate() != null && registerFilterDTO.getToDate() != null) {
			Date formattedFromDate = Helper.formattingDate(registerFilterDTO.getFromDate());
			Date formattedToDate = Helper.formattingDate(registerFilterDTO.getToDate());
			predicates.add(
					criteriaBuilder.between(applicatioRoot.get("dateOfBirth"), formattedFromDate, formattedToDate));
		}

		criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
		List<Register> result = entityManager.createQuery(criteriaQuery).getResultList();
		System.out.println("Kyc approved records result :" + result);
		return result;
	}

}
