package com.ms.common.authentication;

public class ClientAuthenticationBean {
	private String version;
	private String accessKeyId;
	private String signature;
	private String signatureMethod;
	private String timestamp;
	private String signatureVersion;
	private String signatureNonce;
	private String contentMD5;
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getAccessKeyId() {
		return accessKeyId;
	}
	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getSignatureMethod() {
		return signatureMethod;
	}
	public void setSignatureMethod(String signatureMethod) {
		this.signatureMethod = signatureMethod;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getSignatureVersion() {
		return signatureVersion;
	}
	public void setSignatureVersion(String signatureVersion) {
		this.signatureVersion = signatureVersion;
	}
	public String getSignatureNonce() {
		return signatureNonce;
	}
	public void setSignatureNonce(String signatureNonce) {
		this.signatureNonce = signatureNonce;
	}
	public String getContentMD5() {
		return contentMD5;
	}
	public void setContentMD5(String contentMD5) {
		this.contentMD5 = contentMD5;
	}

}
