package com.example.mvvm_sample.di.qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;


@Qualifier
@Retention(RUNTIME)
public @interface Local {
}
