package br.com.stoom.restaddress.configuracao.validacao;

public class ValidationError {
	
	private String field;
	private String error;
	
	public ValidationError(String field, String error) {
		this.field = field;
		this.error = error;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	
	
	

}
