package com.wecodee.employee.application.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mt_images")
public class MtImages {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int imageId;

	private String imageType;

	private String imageData;

	private String fileName;

	private String title;

	private String userId;

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public String getImageData() {
		return imageData;
	}

	public void setImageData(String imageData) {
		this.imageData = imageData;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "MtImages [imageId=" + imageId + ", imageType=" + imageType + ", imageData=" + imageData + ", fileName="
				+ fileName + ", title=" + title + ", userId=" + userId + "]";
	}

}
