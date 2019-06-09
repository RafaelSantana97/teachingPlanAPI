package edu.planner.exception;

public enum ErrorCode {
	
	COURSE_SAVE(1000, "Error while saving a course"),
	COURSE_UPDATE(1001, "Error while updating a course"),
	COURSE_DELETE(1002, "Error while deleting a course"),
	COURSE_DELETE_VIOLATION(1003, "Unable to remove a course in use"),
	COURSE_SEARCH(1004, "Error while querying a course"),
	COURSE_NOT_FOUND(1005, "Course not found"),

	SUBJECT_SAVE(1020, "Error while saving a subject"),
	SUBJECT_UPDATE(1021, "Error while updating a subject"),
	SUBJECT_DELETE(1022, "Error while deleting a subject"),
	SUBJECT_DELETE_VIOLATION(1023, "Unable to remove a subject in use"),
	SUBJECT_SEARCH(1024, "Error while querying a subject"),
	SUBJECT_NOT_FOUND(1025, "Subject not found"),

	CLASS_SAVE(1040, "Error while saving a class"),
	CLASS_UPDATE(1041, "Error while updating a class"),
	CLASS_DELETE(1042, "Error while deleting a class"),
	CLASS_DELETE_VIOLATION(1043, "Unable to remove a class in use"),
	CLASS_SEARCH(1044, "Error while querying a class"),
	CLASS_NOT_FOUND(1045, "Class not found"),

	USER_SAVE(1060, "Error while saving an user"),
	USER_UPDATE(1061, "Error while updating an user"),
	USER_DELETE(1062, "Error while deleting an user"),
	USER_DELETE_VIOLATION(1063, "Unable to remove an user in use"),
	USER_SEARCH(1064, "Error while querying an user"),
	USER_NOT_FOUND(1065, "User not found"),
	USER_WAIT_FOR_APPROVAL(1066, "Please, wait for approval"),
	
	DOMAIN_SAVE(1080, "Error while saving a domain"),
	DOMAIN_UPDATE(1081, "Error while updating a domain"),
	DOMAIN_DELETE(1082, "Error while deleting a domain"),
	DOMAIN_DELETE_VIOLATION(1083, "Unable to remove a domain in use"),
	DOMAIN_SEARCH(1084, "Error while querying a domain"),
	DOMAIN_NOT_FOUND(1085, "Domain not found"),
	
	

	LAST_ERROR(9999, "Just to avoid useless work, don't remove it");
	
	private final int code;
	private final String message;
    
    ErrorCode(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

    @Override
    public String toString() {
    	String message = "";
    	if(this.message != null && this.message.trim().length() > 0) {
    		message = " - " + this.message;
    	}
        return String.valueOf(this.code) + message;
    }
}