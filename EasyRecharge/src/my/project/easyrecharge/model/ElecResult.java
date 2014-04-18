package my.project.easyrecharge.model;

/**
 * Result Back From xml_rpc Request
 * 
 * @author roy
 * @email onecoders@gmail.com
 */

public class ElecResult {

	private String result;
	private String message;
	private String description;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isSuccess() {
		return result.equals("true");
	}

}
