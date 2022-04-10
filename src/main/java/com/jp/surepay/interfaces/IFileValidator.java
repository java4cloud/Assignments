package com.jp.surepay.interfaces;

public interface IFileValidator<I, O> {
    O validate(I i);
}
