package br.com.douglasffilho.UserServices.utils;

public interface EntityFactory<T> {
	public T createValid();

	public T createTest();
}
