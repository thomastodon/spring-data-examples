package io.thomastodon.spring.data.examples.house;

public interface Translator<T, R> {

    R translate(T source);
}
