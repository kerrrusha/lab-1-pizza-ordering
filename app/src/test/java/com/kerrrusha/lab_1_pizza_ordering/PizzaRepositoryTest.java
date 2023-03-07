package com.kerrrusha.lab_1_pizza_ordering;

import org.junit.Test;

import static org.junit.Assert.*;

import com.kerrrusha.lab_1_pizza_ordering.domain.model.Pizza;
import com.kerrrusha.lab_1_pizza_ordering.repository.PizzaRepository;

import java.util.List;

public class PizzaRepositoryTest {

    @Test
    public void isNotEmptyTest() {
        List<Pizza> pizzas = new PizzaRepository().findAll();
        pizzas.forEach(System.out::println);
        assertFalse(pizzas.isEmpty());
    }

}