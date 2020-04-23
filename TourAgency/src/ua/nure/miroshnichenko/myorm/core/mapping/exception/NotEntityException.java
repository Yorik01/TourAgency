package ua.nure.miroshnichenko.myorm.core.mapping.exception;

public class NotEntityException extends MappingException {
	private static final long serialVersionUID = 3356044878929356531L;

	public NotEntityException() {
		super("The entity must have the annotation 'Table' and at last one anntotaion 'Column'");
	}
}
