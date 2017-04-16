package Rx

fun <T> Observable<T?>.nonNull(): Observable<T> {
    return this.filter { value: T? -> value != null }
            .map { value: T? -> value as T }
}