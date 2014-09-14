package tutorial;

public class DummyClass {
	
	private DummyInterface dummyVariable;
	
	private String warningMessage;
	
	public DummyInterface getDummyVariable() {
		return dummyVariable;
	}

	public void setDummyVariable(DummyInterface dummyVariable) {
		this.dummyVariable = dummyVariable;
	}

	public String getWarningMessage() {
		return warningMessage;
	}

	public void setWarningMessage(String warningMessage) {
		this.warningMessage = warningMessage;
	}

	public DummyClass(DummyInterface dummyInterface) {
		this.dummyVariable = dummyInterface;
	}
	
	public void dummyFunction() {
		int result = dummyVariable.dummyFunction();
		
		if (result > 0) {
			this.warningMessage = "OK";
		} else if (result <0) {
			this.warningMessage = "NOK";
		} else {
			this.warningMessage = "";
		}
	}
}
