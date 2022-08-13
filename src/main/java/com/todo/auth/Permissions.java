package com.todo.auth;

public enum Permissions {
	READ("read:permission"),
	CREATE("create:permission"),
	DELETE("delete:permission"),
	UPDATE("update:permission");
	
	private String permission;
	Permissions(String permission) {
		this.permission = permission;
	}
	
	public String getPermission() {
		return this.permission;
	}
}
