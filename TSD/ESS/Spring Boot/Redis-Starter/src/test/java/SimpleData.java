public enum SimpleData {
	// 测试时候的K、V数据
	ZHANGSAN("zhangsan", "10"), LISI("lisi", "20"), OTHER("other", "other");

	private String key;
	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	SimpleData(String _key, String _value) {
		this.key = _key;
		this.value = _value;
	}
}
