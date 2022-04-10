package com.jp.sp.interfaces;

public interface IFileValidator<I, O> {
    O validate(I i);
}
