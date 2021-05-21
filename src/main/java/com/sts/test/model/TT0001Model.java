package com.sts.test.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

// MongoDB - Collection TT0001v2 用
@Document(collection = "TT0001v2")
@Data
public class TT0001Model implements Serializable  {
	private static final long serialVersionUID = -9017118184318057794L;

	private Integer id;
	private String item;
	private Integer qty;
}
