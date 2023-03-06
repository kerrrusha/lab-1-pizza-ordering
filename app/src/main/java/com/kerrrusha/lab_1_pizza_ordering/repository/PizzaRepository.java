package com.kerrrusha.lab_1_pizza_ordering.repository;

import static com.kerrrusha.lab_1_pizza_ordering.util.factory.AdditionCostPairFactory.createStandart;
import static java.util.Arrays.asList;

import com.kerrrusha.lab_1_pizza_ordering.domain.Pizza;
import com.kerrrusha.lab_1_pizza_ordering.domain.SizeWeightCostTriple;

import java.util.List;

public class PizzaRepository implements Repository<Pizza> {

    @Override
    public List<Pizza> findAll() {
        return asList(
                new Pizza(
                        "Gogi",
                        asList(
                                new SizeWeightCostTriple(30, 470, 8340),
                                new SizeWeightCostTriple(40, 640, 12150)
                        ),
                        createStandart()
                ),
                new Pizza(
                        "Baconator",
                        asList(
                                new SizeWeightCostTriple(30, 450, 12500),
                                new SizeWeightCostTriple(40, 610, 15150)
                        ),
                        createStandart()
                )
        );
    }

}
