
package script.system;

public class __ValueRef<T> {

    private Class<T> type;
    
    private T value;

    public __ValueRef() {
    }

    public __ValueRef(Class<T> type) {
	this(type, null);
    }

    public __ValueRef(T value) {
	this(null, value);
    }
    
    public __ValueRef(Class<T> type, T value) {
	this.type = type;
	this.value = value;
    }

    
    public Class<T> getType() {
        return type;
    }

    public T getValue() {
	return value;
    }

    public void setValue(T value) {
	this.value = value;
    }

}
