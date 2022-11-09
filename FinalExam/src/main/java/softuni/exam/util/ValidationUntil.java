package softuni.exam.util;

public interface ValidationUntil {
    <E> boolean isValid(E entity);
}
