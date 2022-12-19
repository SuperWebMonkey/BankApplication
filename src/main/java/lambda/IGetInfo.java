package lambda;

@FunctionalInterface
public interface IGetInfo<T> {
    public abstract void getInfo(T person);
}
