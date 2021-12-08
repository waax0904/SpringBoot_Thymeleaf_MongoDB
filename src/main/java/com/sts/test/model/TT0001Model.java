package com.sts.test.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

// MongoDB - Collection TT0001v2 用
@Document(collection = "TT0001v2")
@Data
public class TT0001Model {
	@Id
	private Integer id;
	private String item;
	private Integer qty;
	private Integer seq;
}
