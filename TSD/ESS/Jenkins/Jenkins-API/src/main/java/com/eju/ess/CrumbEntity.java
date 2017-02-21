package com.eju.ess;

import java.io.Serializable;

import lombok.Data;

@Data
public class CrumbEntity implements Serializable {

	private String _class;
	private String crumb;
	private String crumbRequestField;
}
