package ro.ubb.Lab5.common.Domain.Validators;



public interface Validator<T> {
    void validate(T entity) throws ValidatorException;
}
