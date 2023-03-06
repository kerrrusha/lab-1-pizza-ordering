package com.kerrrusha.lab_1_pizza_ordering.repository;

import java.util.List;

public interface Repository<T> {

    List<T> findAll();

}
