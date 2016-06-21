package de.htwg.se.tpn.persistence.messages;

public class OperationResult {

	public final boolean successful;
	public final String message;
	
	public OperationResult(boolean success, String message) {
		this.successful = success;
		this.message = message;
	}
	
	public OperationResult(boolean success) {
		this(success, "");
	}
}
